package com.babylon.permissions.repository;

import java.util.List;
import java.util.UUID;

import com.babylon.permissions.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

  @Query(value = "select effect, actions, policyItem, *, locales\n" +
      "from role,\n" +
      "     jsonb_array_elements(policy) policyItem,\n" +
      "     jsonb_array_elements(policyItem->'resources') resources,\n" +
      "     jsonb_extract_path(policyItem, 'effect') effect,\n" +
      "     jsonb_extract_path(policyItem, 'actions') actions,\n" +
      "     jsonb_array_elements(resources->'locales') locales\n" +
      "where jsonb_exists_any(locales,array[:locale])",
      nativeQuery = true)
  List<Role> findRole(@Param("locale") String locale);


  @Query(value = "select effect, actions, policyItem, *, locales\n" +
      "from role,\n" +
      "     jsonb_array_elements(policy) policyItem,\n" +
      "     jsonb_array_elements(policyItem->'resources') resources,\n" +
      "     jsonb_extract_path(policyItem, 'effect') effect,\n" +
      "     jsonb_extract_path(policyItem, 'actions') actions,\n" +
      "     jsonb_array_elements(resources->'locales') locales\n" +
      "where name = :rolename AND jsonb_exists_any(locales,array[:locale])",
      nativeQuery = true)
  List<Role> findRole(@Param("rolename") String rolename, @Param("locale") String locale);


  @Query(value = "select * \n" +
      "from role,\n" +
      "     jsonb_array_elements(policy) policyItem,\n" +
      "     jsonb_array_elements(policyItem->'resources') resources,\n" +
      "     jsonb_extract_path(policyItem, 'effect') effect,\n" +
      "     jsonb_extract_path(policyItem, 'actions') actions,\n" +
      "     jsonb_array_elements(resources->'projects') projects,\n" +
      "     jsonb_array_elements(resources->'locales') locales\n" +
      "where name = :rolename " +
      "     AND jsonb_exists_any(locales,array[:locale])" +
      "     AND jsonb_extract_path_text(projects, 'id') = :project",
      nativeQuery = true)
  Role findRole(@Param("rolename") String rolename,
                @Param("locale") String locale,
                @Param("project") String project);


  @Query(value = "select actions \n" +
      "from role,\n" +
      "     jsonb_array_elements(policy) policyItem,\n" +
      "     jsonb_array_elements(policyItem->'resources') resources,\n" +
      "     jsonb_extract_path(policyItem, 'effect') effect,\n" +
      "     jsonb_extract_path_text(policyItem, 'actions') actions,\n" +
      "     jsonb_array_elements(resources->'projects') projects,\n" +
      "     jsonb_array_elements(resources->'locales') locales\n" +
      "where name = :rolename " +
      "     AND jsonb_exists_any(locales,array[:locale])" +
      "     AND jsonb_extract_path_text(projects, 'id') = :project",
      nativeQuery = true)
  String findActions(@Param("rolename") String rolename,
                     @Param("locale") String locale,
                     @Param("project") String project);
}
