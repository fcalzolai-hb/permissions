package integration;

import java.io.IOException;
import java.nio.charset.Charset;

import com.babylon.permissions.PermissionsApplication;
import com.babylon.permissions.dao.Role;
import com.babylon.permissions.repository.RoleRepository;
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
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {PermissionsApplication.class})
public class RoleRepositoryTest {

  private static final String TRANSLATION_MANAGER = "translation_manager";
  private static final String REVIEWER = "reviewer";
  private static final String SHARED_PROJECT = "ea8b601b-0ebc-4310-b512-ee7df8240e9f";
  private static final String CREATE_REGIONAL_KEY = "create_regional_key";

  @Autowired
  private NowProvider nowProvider;

  @Autowired
  private RoleRepository roleRepository;

  @Test
  public void findRoleByRoleNameAndProject() throws IOException {
    initDB();
    Role role = roleRepository.findRole(TRANSLATION_MANAGER, "en-GB", SHARED_PROJECT);
    assertNotNull(role);

    role = roleRepository.findRole(REVIEWER, "en-GB", SHARED_PROJECT);
    assertNotNull(role);
  }

  @Test
  public void findRoleByRoleName() throws IOException {
    initDB();
    Role role1 = roleRepository.findRole(TRANSLATION_MANAGER, "es-ES");
    assertNotNull(role1);

    Role role2 = roleRepository.findRole(REVIEWER, "es-ES");
    assertNull(role2);
  }

  @Test
  public void findRole() throws IOException {
    initDB();
    Role role = roleRepository.findRole("es-ES");
    assertNotNull(role);
  }

  private Role createRoleAndSave(String roleName, String fileName) throws IOException {
    String jsonPolicy = loadStringFromFile(fileName);

    Role role = Role.builder()
        .id(randomUUID())
        .name(roleName)
        .policy(jsonPolicy)
        .createdAt(nowProvider.nowUtc())
        .updatedAt(nowProvider.nowUtc())
        .build();

    return roleRepository.save(role);
  }

  private String loadStringFromFile(String fileName) throws IOException {
    return Resources.toString(getResource(fileName), Charset.defaultCharset());
  }

  private void initDB() throws IOException {
    roleRepository.deleteAll();

    createRoleAndSave(REVIEWER, "policies/reviewer_policy.json");
    createRoleAndSave(TRANSLATION_MANAGER, "policies/translation_manager_policy.json");
  }
}
