INSERT INTO role (id, type) VALUES (1, "Client"), (2, "Supplier")
ON DUPLICATE KEY UPDATE type=type;