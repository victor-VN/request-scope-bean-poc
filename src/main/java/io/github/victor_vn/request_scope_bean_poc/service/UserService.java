package io.github.victor_vn.request_scope_bean_poc.service;

import io.github.victor_vn.request_scope_bean_poc.domain.ProcessDto;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private ProcessDto globalDto;

    public UserService(ProcessDto globalDto) {
        this.globalDto = globalDto;
    }

    public void processDto(){
        globalDto.setOutput(
                globalDto.getUserData() + globalDto.getInput().toString()
        );
    }
}
