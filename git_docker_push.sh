echo "Digite a tag para publicação"
read TAG

git tag $TAG
git push --tags

docker build -t lcalixto21/hello_bank:$TAG .
docker push lcalixto21/hello_bank:$TAG