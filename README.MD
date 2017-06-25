DB

docker run -p 3306:3306  --name demo-db  --env MYSQL_USER=admin  --env MYSQL_PASSWORD=admin  --env MYSQL_DATABASE=demo  wangxian/alpine-mysql
