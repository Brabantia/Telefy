package telefy;

import java.nio.file.Path;

public class Resource {
	public final String path, file, extension;
	public final FileType type;
	public final byte[] data;

	public Resource(String path, String file, String extension, byte[] data) {
		this.path = path;
		this.file = file;
		this.extension = extension;
		this.data = data;
		this.type = FileType.getType(extension);
	}

	public Resource(Path fullPath, byte[] data) {
		this(getPath(fullPath), fullPath.getFileName().toString(), getExtension(fullPath), data);
	}

	public int length() {
		return data.length;
	}

	@Override
	public String toString() {
		return "Resource[path=" + path + ", file=" + file + ", extension=" + extension +  ", length=" + length() + "]";
	}

	public static String getPath(Path fullPath) {
		Path parent = fullPath.getParent();
		if (parent == null) {
			return "/";
		}
		return parent.toString();
	}

	public static String getFile(String fullPath) {
		// Remove trailing slashes which can exist for folders.
		while (fullPath.charAt(fullPath.length()-1) == '/') {
			fullPath = fullPath.substring(0, fullPath.length()-1);
		}

		if (!fullPath.contains("/")) {
			return fullPath;
		}

		return fullPath.substring(fullPath.lastIndexOf("/")+1);
	}

	public static String getExtension(Path fullPath) {
		String file = fullPath.getFileName().toString();

		if (!file.contains(".") || file.lastIndexOf(".") == file.length()-1) {
			return "";
		}

		return file.substring(file.lastIndexOf(".")+1);
	}
}