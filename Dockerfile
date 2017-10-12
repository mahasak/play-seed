FROM alpine/java8

COPY target/universal/*.zip /root/build.zip

RUN unzip /root/build.zip -d /root/release

# Define working directory.
WORKDIR /root

# Define commonly used JAVA_HOME variable
ENV JAVA_OPTS -server -Xms64M -Xmx128M
ENV LANGUAGE en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LC_ALL en_US.UTF-8

# Define default command.
CMD ["/root/release/bin/play-seed", "-Dhttp.address=0.0.0.0"]
