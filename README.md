# Permission spike project

## TODO
1. Decide if we want to use `json` or `jsonb` PSQL type
    1. Both `json` and `jsonb` fields are human readable: compare Role and Member tables. 
    [Here](https://www.compose.com/articles/faster-operations-with-the-jsonb-data-type-in-postgresql/) an interesting 
    article about "_jsonb vs. json_" topic.
2. Decide if we want to use vlad mihalcea dependency `hibernate-types-52` 
3. Run queries against view instead of using join

##