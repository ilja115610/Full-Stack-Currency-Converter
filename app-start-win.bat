cd db-postgres
docker build -t exchange-app-img .
docker run -d -p 5432:5432 --rm -v logs:/var/lib/postgresql/data --name exchange-app-ctr exchange-app-img

