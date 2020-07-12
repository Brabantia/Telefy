package telefy.model;

import telefy.entity.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class FileResourceModel implements ResourceModel {
	private final HashMap<String, Resource> resources = new HashMap<>();
	private final Path resourceFolder;
	private boolean loaded = false;

	public FileResourceModel(String resourceFolder) throws IOException {
		File folder = (new File(resourceFolder)).getAbsoluteFile();
		this.resourceFolder = folder.toPath();
	}

	public void loadResources() throws IOException {
		File folder = new File(this.resourceFolder.toUri());
		if (!folder.exists()) {
			throw new IOException("Cannot load resources from a folder that doesn't even exist: " + folder);
		}
				
		this.resources.clear();
		addFolderFiles(folder);
		this.loaded = true;
	}

	private void addFolderFiles(File folder) throws IOException {
		if (folder == null) {
			return;
		}
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				addFolderFiles(file);
				continue;
			}

			Path path = file.toPath();
			byte[] data = Files.readAllBytes(path);

			if (data != null) {
				path = this.resourceFolder.relativize(path);
				this.resources.put(path.toString(), new Resource(path, data));
			}
		}
	}

	@Override
	public Resource get(String path) {
		if (!loaded) {
			throw new RuntimeException("File Resources have not been loaded before being accessed");
		}
		path = path.replace("/", FileSystems.getDefault().getSeparator());
		while (path.startsWith(FileSystems.getDefault().getSeparator())) {
			path = path.substring(1);
		}
		return resources.get(path);
	}
	
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		text.append(getClass() +"[\n");
		for (HashMap.Entry<String, Resource> entry : this.resources.entrySet()) {
			text.append(entry.getKey()+": "+entry.getValue()+"\n");
		}
		text.append("]");
		return text.toString();
	}
}