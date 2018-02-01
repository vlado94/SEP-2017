# SEP-2017
ISOK

Keycloak pokrecemo tako sto se pozicioniramo u bin fajl keycloak-a i u komandnoj liniji napisemo:
standalone.bat -Djboss.socket.binding.port-offset=100 -b=0.0.0.0


Insurance app pokrecemo na portu 4500, a external app na portu 4300


external app za mobilnu aplikaciju se pokrece sa --host 0.0.0.0

acquirer app pokrecemo na portu 4600

pokrecemo dve banke na 8400 i 8500
