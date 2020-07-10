package telefy.view;

import telefy.entity.Resource;

public class FileView implements View {
	private final Resource resource;

	public FileView(Resource resource) {
		this.resource = resource;
	}

	@Override
	public byte[] getBytes() {
		return this.resource.getData();
	}

	@Override
	public int length() {
		return getBytes().length;
	}

	@Override
	public String getMime() {
		return this.resource.getType().getMime();
	}
}
