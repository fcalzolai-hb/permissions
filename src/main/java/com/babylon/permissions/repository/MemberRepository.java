package com.babylon.permissions.repository;

import java.util.UUID;

import com.babylon.permissions.dao.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {


}
