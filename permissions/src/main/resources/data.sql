INSERT INTO privileges (id, modified, created, name) VALUES
  (1, NOW(), NOW(), 'ORG_READ'),
  (2, NOW(), NOW(), 'ORG_EDIT'),
  (3, NOW(), NOW(), 'ORG_UPDATE_PERMISSIONS'),
  (4, NOW(), NOW(), 'ORG_READ_PERMISSIONS'),
  (5, NOW(), NOW(), 'ORG_CREATE_PROJECT'),
  (6, NOW(), NOW(), 'ORG_DELETE'),
  (7, NOW(), NOW(), 'PROJECT_READ'),
  (8, NOW(), NOW(), 'PROJECT_EDIT'),
  (9, NOW(), NOW(), 'PROJECT_READ_COMMITS'),
  (10, NOW(), NOW(), 'PROJECT_CREATE_BRANCH'),
  (11, NOW(), NOW(), 'PROJECT_DELETE'),
  (12, NOW(), NOW(), 'PROJECT_UPDATE_PERMISSIONS'),
  (13, NOW(), NOW(), 'PROJECT_READ_PERMISSIONS'),
  (14, NOW(), NOW(), 'PROJECT_CREATE_WEBHOOKS'),
  (15, NOW(), NOW(), 'BRANCH_READ'),
  (16, NOW(), NOW(), 'BRANCH_EDIT_CONTENT'),
  (17, NOW(), NOW(), 'BRANCH_DELETE'),
  (18, NOW(), NOW(), 'BRANCH_UPDATE_PERMISSIONS'),
  (19, NOW(), NOW(), 'BRANCH_READ_PERMISSIONS');

INSERT INTO roles (id, modified, created, name) VALUES
  (0, NOW(), NOW(), 'admin');

INSERT INTO roles_privileges (role_id, privilege_id) VALUES
  (0, 1),
  (0, 2),
  (0, 3),
  (0, 4),
  (0, 5),
  (0, 6),
  (0, 7),
  (0, 8),
  (0, 9),
  (0, 10),
  (0, 11),
  (0, 12),
  (0, 13),
  (0, 14),
  (0, 15),
  (0, 16),
  (0, 17),
  (0, 18),
  (0, 19);