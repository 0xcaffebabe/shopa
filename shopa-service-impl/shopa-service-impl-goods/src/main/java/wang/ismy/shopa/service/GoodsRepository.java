package wang.ismy.shopa.service;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author MY
 * @date 2020/4/2 11:36
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, String> {
}
