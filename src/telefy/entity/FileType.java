package telefy.entity;

public enum FileType {
	CSS("text/css"),
	CSV("text/csv"),
	HTML("text/html"),
	TXT("text/plain"),
	XML("text/xml"),
	JSON("application/json"),
	JAVASCRIPT("application/javascript"),
	PDF("application/pdf"),
	ZIP("application/zip"),
	BMP("image/bmp"),
	GIF("image/gif"),
	ICO("image/x-icon"),
	JPG("image/jpeg"),
	PNG("image/png"),
	SVG("image/svg+xml"),
	MP3("audio/mp3"),
	EOT("application/vnd.ms-fontobject"),
	OTF("font/otf"),
	TTF("font/ttf"),
	WOFF("application/font-woff"),
	WOFF2("application/font-woff2");

	private final String mime;

	FileType(String mime) {
		this.mime = mime;
	}

	public String getMime() {
		return this.mime;
	}

	public static FileType getType(String extension) {
		if (extension == null) {
			return null;
		}

		switch (extension.toLowerCase()) {
			case "css":
				return CSS;
			case "csv":
				return CSV;
			case "htm":
			case "html":
				return HTML;
			case "txt":
				return TXT;
			case "xml":
				return XML;
			case "json":
				return JSON;
			case "js":
				return JAVASCRIPT;
			case "pdf":
				return PDF;
			case "zip":
				return ZIP;
			case "bmp":
				return BMP;
			case "gif":
				return GIF;
			case "ico":
				return ICO;
			case "jpg":
			case "jpeg":
				return JPG;
			case "png":
				return PNG;
			case "svg":
				return SVG;
			case "mp3":
				return MP3;
			case "eot":
				return EOT;
			case "otf":
				return OTF;
			case "ttf":
				return TTF;
			case "woff":
				return WOFF;
			case "woff2":
				return WOFF2;
			default:
				return null;
		}
	}
}