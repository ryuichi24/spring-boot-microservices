# https://serverfault.com/a/932210

FROM nginx:1.21.6-alpine

COPY ./conf.d /etc/nginx/conf.d

ENTRYPOINT ["nginx"]
CMD ["-g", "daemon off;"]
