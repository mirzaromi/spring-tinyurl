docker run -d \
  -p 3000:3000 \
  --network monitoring \
  --name=grafana \
  grafana/grafana
