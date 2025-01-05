package io.github.victor_vn.request_scope_bean_poc.feign;

import io.github.victor_vn.request_scope_bean_poc.feign.dto.UserPlaceholderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "external-api", url = "https://jsonplaceholder.typicode.com")
public interface PlaceholderFeignClient {

    @GetMapping(name = "external-api", path = "/posts/{id}")
    public UserPlaceholderDto getUserInfo(@PathVariable int id);
}
