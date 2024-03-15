package com.alvin.alvinvoucher.service.impl;

import com.alvin.alvinvoucher.entity.Role;
import com.alvin.alvinvoucher.repository.RoleRepository;
import com.alvin.alvinvoucher.repository.impl.RoleRepositoryImpl;
import com.alvin.alvinvoucher.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = roleRepository.findByRoleName(role.getName());
        if(!optionalRole.isEmpty()) {
            return optionalRole.get();
        }
        return roleRepository.save(role);
    }
}
