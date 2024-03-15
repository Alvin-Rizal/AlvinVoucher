package com.alvin.alvinvoucher.service;

import com.alvin.alvinvoucher.entity.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
