create table categories_and_tasks (
    id serial primary key,
    tasks_id int not null references tasks(id),
    categories_id int not null references categories(id)
)