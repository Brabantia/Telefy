package telefy.model;

import telefy.entity.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import javax.swing.plaf.metal.MetalIconFactory;

public class FileResourceModel implements ResourceModel {
	private final HashMap<String, Resource> resources = new HashMap<>();
	private final Path resourceFolder;

	public FileResourceModel(String resourceFolder) throws IOException {
		File folder = new File(resourceFolder);
		this.resourceFolder = folder.toPath();

		loadResources();

		for (HashMap.Entry<String, Resource> entry : this.resources.entrySet()) {
			System.out.println(entry.getKey()+": "+entry.getValue());
		}
	}

	public void loadResources() throws IOException {
		this.resources.clear();
		addFolderFiles(new File(this.resourceFolder.toUri()));
	}

	private void addFolderFiles(File folder) throws IOException {
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
		path = path.replace("/", FileSystems.getDefault().getSeparator());
		while (path.startsWith(FileSystems.getDefault().getSeparator())) {
			path = path.substring(1);
		}
		return resources.get(path);
	}
}