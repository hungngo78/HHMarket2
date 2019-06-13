FROM java:8

COPY HHMarketRest.jar restapi.jar
COPY application.properties application.properties
ADD HHMarketRest_lib HHMarketRest_lib

COPY start.sh start.sh
RUN chmod +x start.sh
RUN sed -i -e 's/\r$//' start.sh
#ENTRYPOINT ["/start.sh"]    -> start it after get into container -> more flexible