package integration.old.version;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.babylon.permissions.PermissionsApplication;
import com.babylon.permissions.dao.Role;
import com.babylon.permissions.repository.old.RoleRepository_0;
import com.babylon.permissions.sysytem.NowProvider;
import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.io.Resources.getResource;
import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {PermissionsApplication.class})
public class RoleRepositoryTest_0 {

  @Autowired
  private NowProvider nowProvider;

  @Autowired
  private RoleRepository_0 roleRepository;

  @Test
  public void findRoleByRoleNameAndProject() throws IOException {
    Role role1 = roleRepository.findRole("role2", "en-GB", "ea8b601b-0ebc-4310-b512-ee7df8240e99");
    assertNotNull(role1);

    Role role2 = roleRepository.findRole("role2", "en-GB", "ea8b601b-0ebc-4310-b512-ee7df8240e9f");
    assertNull(role2);
  }

  @Test
  public void findRoleByRoleName() throws IOException {
    Role role1 = roleRepository.findRole("role1", "es-ES");
    assertNotNull(role1);

    Role role2 = roleRepository.findRole("role2", "es-ES");
    assertNull(role2);
  }

  @Test
  public void findRole() throws IOException {
//    createRoleAndSave("./policies/reviewer_policy.json");
    Role role = roleRepository.findRole("es-ES");
    assertNotNull(role);
  }

  @Test
  public void findAllByProjectWithParam_0() {
    List<Role> roles = roleRepository.findAllByProject_1("ea8b601b-0ebc-4310-b512-ee7df8240e9f");
    assertTrue(roles.size() > 0);
  }

  @Test
  public void findAllByProjectWithParam_1() {
    List<Role> roles = roleRepository.findAllByProject_1("ea8b601b-0ebc-4310-b512-ee7df8240e9f");
    assertTrue(roles.size() > 0);
  }

  @Test
  public void findAllByProjectWithParam_2() {
    List<Role> roles = roleRepository.findAllByProject_2("ea8b601b-0ebc-4310-b512-ee7df8240e9f");
    assertTrue(roles.size() > 0);
  }

  @Test
  public void findAllByProject() {
    List<Role> roles = roleRepository.findAllByProject();
    assertTrue(roles.size() > 0);
  }

  @Test
  public void findAllWithNotNullPolicy() {
    List<Role> roles = roleRepository.findAllWithNotNullPolicy(0);
    assertTrue(roles.size() > 0);
  }

  @Test
  public void findByParam() throws IOException {
    Role role = createRoleAndSave("policies/reviewer_policy.json");
    Role byParam = roleRepository.findByParam(role.getId());
    assertNotNull(byParam);
  }

  @Test
  public void save() throws IOException {
    Role save = createRoleAndSave("policies/reviewer_policy.json");
    assertNotNull(save.getId());
  }

  private Role createRoleAndSave(String fileName) throws IOException {
    String jsonPolicy = loadStringFromFile(fileName);

    Role role = Role.builder()
        .id(randomUUID())
        .name(randomUUID().toString())
        .policy(jsonPolicy)
        .createdAt(nowProvider.nowUtc())
        .updatedAt(nowProvider.nowUtc())
        .build();

    return roleRepository.save(role);
  }

  private String loadStringFromFile(String fileName) throws IOException {
    return Resources.toString(getResource(fileName), Charset.defaultCharset());
  }
}
