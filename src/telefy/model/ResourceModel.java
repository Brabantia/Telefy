package telefy.model;

import telefy.entity.Resource;

public interface ResourceModel {
	Resource get(String path);
}