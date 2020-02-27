package com.babylon.permissions.repository.old;

import java.util.List;
import java.util.UUID;

import com.babylon.permissions.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository_0 extends JpaRepository<Role, UUID> {

  @Query("SELECT r FROM Role r WHERE r.id = :id")
  Role findByParam(@Param("id") UUID id);

  @Query("SELECT r " +
      " FROM Role r " +
      " WHERE 'policy -> :index' is not null")
  List<Role> findAllWithNotNullPolicy(@Param("index") int index);

  @Query(value = "SELECT * FROM Role WHERE policy @> " +
      "'[{" +
      "   \"resources\": [{" +
      "      \"projects\": [{" +
      "         \"id\": \"ea8b601b-0ebc-4310-b512-ee7df8240e9g\" " +
      "      }]" +
      "   }]" +
      "}]'",
      nativeQuery = true)
  List<Role> findAllByProject();

  @Query(value = "SELECT * FROM Role WHERE policy @> " +
      "'[{" +
      "   \"resources\": [{" +
      "      \"projects\": [{" +
      "         \"id\": \":projectId\" " +
      "      }]" +
      "   }]" +
      "}]'",
      nativeQuery = true)
  List<Role> findAllByProject_0(@Param("projectId") String projectId);

  @Query(value = "SELECT * FROM Role WHERE policy @> " +
      "'[{" +
      "   \"resources\": [{" +
      "      \"projects\": [{" +
      "         \"id\": ?1 " +
      "      }]" +
      "   }]" +
      "}]'",
      nativeQuery = true)
  List<Role> findAllByProject_1(String projectId);

  @Query(value = "SELECT * FROM Role WHERE policy @> " +
      "'[{" +
      "   \"resources\": [{" +
      "      \"projects\": [{" +
      "         \"id\": \"?1\" " +
      "      }]" +
      "   }]" +
      "}]'",
      nativeQuery = true)
  List<Role> findAllByProject_2(String projectId);


  @Query(value = "select effect, actions, policyItem, *, locales\n" +
      "from role,\n" +
      "     jsonb_array_elements(policy) policyItem,\n" +
      "     jsonb_array_elements(policyItem->'resources') resources,\n" +
      "     jsonb_extract_path(policyItem, 'effect') effect,\n" +
      "     jsonb_extract_path(policyItem, 'actions') actions,\n" +
      "     jsonb_array_elements(resources->'locales') locales\n" +
      "where jsonb_exists_any(locales,array[:locale])",
      nativeQuery = true)
  Role findRole(@Param("locale") String locale);


  @Query(value = "select effect, actions, policyItem, *, locales\n" +
      "from role,\n" +
      "     jsonb_array_elements(policy) policyItem,\n" +
      "     jsonb_array_elements(policyItem->'resources') resources,\n" +
      "     jsonb_extract_path(policyItem, 'effect') effect,\n" +
      "     jsonb_extract_path(policyItem, 'actions') actions,\n" +
      "     jsonb_array_elements(resources->'locales') locales\n" +
      "where name = :rolename AND jsonb_exists_any(locales,array[:locale])",
      nativeQuery = true)
  Role findRole(@Param("rolename") String rolename, @Param("locale") String locale);


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
}
