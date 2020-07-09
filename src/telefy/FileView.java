package telefy;

public class FileView implements View {
	private final Resource resource;

	public FileView(Resource resource) {
		this.resource = resource;
	}

	@Override
	public byte[] getBytes() {
		return this.resource.data;
	}

	@Override
	public int length() {
		return getBytes().length;
	}

	@Override
	public String getMime() {
		return this.resource.type.getMime();
	}
}
