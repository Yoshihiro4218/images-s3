FROM nginx:latest

ENV LANG ja_jp.UTF-8
ENV TZ Asia/Tokyo

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY dist /usr/share/dist

COPY ./docker/nginx/default.conf /etc/nginx/conf.d/
COPY ./docker/nginx/nginx.conf /etc/nginx

RUN chgrp -R root /var/cache/nginx /var/run /dev/stdout && \
    chmod -R 777 /var/cache/nginx /var/run /dev/stdout

EXPOSE 80

COPY ./docker/nginx-config/entrypoint.sh /
RUN chmod a+x /entrypoint.sh

CMD["/entrypoint.sh"]
