package com.jiksoft.library.services;

import java.util.List;

import com.jiksoft.library.bo.MembershipBO;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.MembershipException;

public interface MembershipService {

    MembershipBO create(MembershipBO membershipBo);

    MembershipBO findById(String id);

    List<MembershipBO> findAll();

    MembershipBO update(String id, MembershipBO membershipBo);

    void delete(String id);

    void validateUserMembership(String userId, String libraryId) throws MembershipException;
}