FROM andyg42/vidispine-server:5.2

USER root
COPY shiro.ini /var/lib/vidispine/config/shiro.ini
COPY vidispine-auth-jar.jar /usr/share/vidispine/server/lib/ext/gnm-jwt-auth.jar
USER vidispine
