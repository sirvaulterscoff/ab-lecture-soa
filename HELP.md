# Запуск ELK

ELK_VERSION=6.4.2 docker compose -f docker-compose-elk.yml up 

# Запуск prometheus

docker-compose -f docker-compose-prometheus.yml up