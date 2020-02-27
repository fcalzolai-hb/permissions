CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table member
(
 id                       uuid DEFAULT uuid_generate_v4 (),
 external_id              text NOT NULL,
 encrypted_full_name      bytea NOT NULL,
 encrypted_email          bytea NOT NULL,
 policy                   json,
 created_at               timestamp(3) NOT NULL DEFAULT current_timestamp,
 updated_at               timestamp(3) NOT NULL DEFAULT current_timestamp,
 last_active_at           timestamp(3) NOT NULL DEFAULT current_timestamp,
 PRIMARY KEY (id),
 CONSTRAINT UQ_external_id UNIQUE (external_id)
);

CREATE INDEX external_id_idx ON member USING hash (external_id);

create table role
(
 id          uuid DEFAULT uuid_generate_v4 (),
 name        text NOT NULL,
 policy      jsonb,
 created_at  timestamp(3) NOT NULL DEFAULT current_timestamp,
 updated_at  timestamp(3) NOT NULL DEFAULT current_timestamp,
 PRIMARY KEY (id),
 CONSTRAINT UQ_role_name UNIQUE (name)
);


create table member_role
(
 id          uuid DEFAULT uuid_generate_v4 (),
 role_id     uuid references role(id),
 user_id     uuid references member(id),
 created_at  timestamp(3) NOT NULL DEFAULT current_timestamp, --not a must if difficult to implement
 PRIMARY KEY (id)
);

CREATE INDEX rol_role_id_idx ON member_role USING hash (role_id);
CREATE INDEX rol_user_id_idx ON member_role USING hash (user_id);

create table la_group
(
 id          uuid DEFAULT uuid_generate_v4 (),
 name        text NOT NULL,
 policy      jsonb,
 created_at  timestamp(3) NOT NULL DEFAULT current_timestamp,
 PRIMARY KEY (id),
 CONSTRAINT UQ_group_name UNIQUE (name)
);

create table member_la_group
(
 id          uuid DEFAULT uuid_generate_v4 (),
 group_id    uuid references la_group(id),
 user_id     uuid references member(id),
 created_at  timestamp(3) NOT NULL DEFAULT current_timestamp, --not a must if difficult to implement
 PRIMARY KEY (id)

);

CREATE INDEX grp_group_id_idx ON member_la_group USING hash (group_id);
CREATE INDEX grp_user_id_idx ON member_la_group USING hash (user_id);

create table la_group_roles
(
 id          uuid DEFAULT uuid_generate_v4 (),
 group_id    uuid references la_group(id),
 role_id     uuid references role(id),
 created_at     timestamp(3) NOT NULL DEFAULT current_timestamp, --not a must if difficult to implement
 PRIMARY KEY (id)
);
