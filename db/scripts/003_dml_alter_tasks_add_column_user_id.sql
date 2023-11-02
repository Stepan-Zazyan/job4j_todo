alter table tasks add column user_id int references users(id);

alter table tasks drop column user_id;