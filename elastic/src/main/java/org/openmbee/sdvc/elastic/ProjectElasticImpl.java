package org.openmbee.sdvc.elastic;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.openmbee.sdvc.core.services.ProjectIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ProjectElasticImpl implements ProjectIndex {

    protected RestHighLevelClient client;

    @Autowired
    public void setRestHighLevelClient(@Qualifier("clientElastic") RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public void create(String projectId) {
        create(projectId, "default");
    }

    @Override
    public void create(String projectId, String projectType) {
        try {
            String index = projectId.toLowerCase();
            CreateIndexRequest commitIndex = new CreateIndexRequest(index + "_commit");
            commitIndex.mapping("_doc", getCommitMapping(), XContentType.JSON);
            CreateIndexRequest nodeIndex = new CreateIndexRequest(index + "_node");
            nodeIndex.mapping("_doc", getNodeMapping(projectType), XContentType.JSON);
            createIndex(commitIndex);
            createIndex(nodeIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createIndex(CreateIndexRequest request) throws IOException {
        client.indices().create(request, RequestOptions.DEFAULT).isAcknowledged();
    }

    @Override
    public void delete(String projectId) {
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(projectId.toLowerCase() + "_node");
            DeleteIndexRequest request2 = new DeleteIndexRequest(projectId.toLowerCase() + "_commit");
            client.indices().delete(request, RequestOptions.DEFAULT).isAcknowledged();
            client.indices().delete(request2, RequestOptions.DEFAULT).isAcknowledged();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCommitMapping() throws IOException {
        InputStream in = new ClassPathResource("/elastic_mappings/commit.json")
            .getInputStream();
        return read(in);
    }

    private String getNodeMapping(String type) throws IOException {
        InputStream in = new ClassPathResource("/elastic_mappings/" + type + "_node.json")
            .getInputStream();
        if (in == null) {
            in = new ClassPathResource("/elastic_mappings/default_node.json")
                .getInputStream();
        }
        return read(in);
    }

    private String read(InputStream in) throws IOException {
        if (in == null) {
            throw new IOException("Resource not found!");
        }
        String text;
        try (Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
        }
        return text;
    }
}
