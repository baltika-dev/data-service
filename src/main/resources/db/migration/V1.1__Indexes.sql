CREATE INDEX IF NOT EXISTS users_id_idx ON users USING btree (id);
CREATE INDEX IF NOT EXISTS users_email_idx ON users USING btree (email);
CREATE INDEX IF NOT EXISTS users_created_at_idx ON users USING btree (created_at);

CREATE INDEX IF NOT EXISTS alias_user_id_idx ON alias USING btree (user_id);
CREATE INDEX IF NOT EXISTS alias_ally_idx ON alias USING btree (ally);
