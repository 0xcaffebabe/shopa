package wang.ismy.shopa.goods;

import org.springframework.web.bind.annotation.GetMapping;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.entity.ProductDTO;

import java.util.List;

/**
 * @author MY
 * @date 2020/4/2 11:27
 */
public interface GoodsService {
    @GetMapping("/search")
    BaseResponse<List<ProductDTO>> search(String name);
}
