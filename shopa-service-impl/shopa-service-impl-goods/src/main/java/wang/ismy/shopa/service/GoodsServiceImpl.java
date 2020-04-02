package wang.ismy.shopa.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.shopa.common.BaseApiService;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.entity.ProductDTO;
import wang.ismy.shopa.goods.GoodsService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MY
 * @date 2020/4/2 11:32
 */
@RestController
public class GoodsServiceImpl extends BaseApiService implements GoodsService {

    @Autowired
    private GoodsRepository repository;

    @Override
    public BaseResponse<List<ProductDTO>> search(String name) {

        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        // 模拟查询
        builder.must(QueryBuilders.fuzzyQuery("name", name));
        Pageable pageable = PageRequest.of(0,5);
        Page<Goods> page = repository.search(builder, pageable);
        List<Goods> content = page.getContent();
        var result = content.stream().map(g -> {
            ProductDTO dto = new ProductDTO();
            dto.setName(g.getName());
            dto.setContent(g.getContent());
            return dto;
        }).collect(Collectors.toList());
        return setResultSuccess(result);
    }
}
