package com.progresssoft.test;

import java.util.List;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

public class TestDocker {

	public static void main(String[] args) {
		DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
		DockerClient client = DockerClientBuilder.getInstance(config).build();

		List<Container> consList = client.listContainersCmd().exec();
		for (Container con : consList) {
			System.out.println("container" + con.getImage());
		}
	}
}
