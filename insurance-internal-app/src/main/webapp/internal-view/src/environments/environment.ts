// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
	production: false,
	KEYCLOAK_URL: 'http://localhost:8180/auth',
	KEYCLOAK_REALM: 'isok',
	KEYCLOAK_CLIENTID: 'isok-frontend-employee',
	BACKEND_URL: 'http://localhost:8082/internal',
	JAVA_CARD_URL: 'http://192.168.0.103:8086'

};
