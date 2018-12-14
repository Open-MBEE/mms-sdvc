package org.openmbee.sdvc.crud.repositories.commit;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openmbee.sdvc.json.BaseJson;

public interface CommitIndexDAO {

    Map<String, Object> findByIndexId(String indexId) throws IOException ;

    List<Map<String, Object>> findByIndexIds(Set<String> indexIds);

    void indexAll(Collection<? extends BaseJson> jsons) throws IOException;

    void index(BaseJson json) throws IOException;

    Map<String, Object> findById(String elasticId) throws IOException;

    List<Map<String, Object>> findAllById(Set<String> elasticIds) throws IOException;

}
