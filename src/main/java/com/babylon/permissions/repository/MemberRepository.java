package com.babylon.permissions.repository;

import java.util.List;
import java.util.UUID;

import com.babylon.permissions.dao.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

  @Query("SELECT m FROM Member m WHERE m.id = :id")
  Member findByParam(@Param("id") UUID id);

  @Query("SELECT m " +
      " FROM Member m " +
      " WHERE 'm.policy -> :index' is not null")
  List<Member> findAllWithNotNullPolicy(@Param("index") int index);


  @Query(value = "select * " +
      "from member, jsonb_array_elements(policy#>'{resources}') resources, jsonb_array_elements(resources->'locales') locales " +
      "where jsonb_exists(locales, :locale)",
      nativeQuery = true)
  Member findMember(@Param("locale") String locale);

}
