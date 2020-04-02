package wang.ismy.shopa.service;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author MY
 * @date 2020/4/2 11:36
 */
@Document(indexName = "goods",type = "goods")
@Data
public class Goods {
    @Id
    private String id;
    private String name;
    private String content;
}
