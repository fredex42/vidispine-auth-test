## What is it?

This is a proof-of-concept of a third-party JAR for Vidispine Server (https://www.vidispine.com) that will create
user accounts from bearer token claims on login.

## What is it meant to do?

When suitably configured (see later), Vidispine Server will accept bearer tokens as a legitimate form of authentication.
However, it still needs a user profile in its own database to perform authorization (i.e. is user X allowed to do action Y),
so by default if no corresponding user exists in the database then authentication is rejected with a "user does not exist"
message.
This class maps data in the Claims section of the JWT to a Vidispine user profile and returns it to the app so a user profile
is created on first access

## How do I use it?

You need to configure shiro.ini and place it in /var/lib/vidispine/config.  See shiro.ini in the `test-environment` directory
for a working example.

Firstly, you need to ensure that bearer token authentication is enabled 
(see https://apidoc.vidispine.com/latest/security/auth.html#example-static-public-key).
Then, you need to compile the code present here into a JAR file (I am using Intellij for this) and place the jar in
/usr/share/vidispine/server/lib/ext.
Next, update the shiro.ini as per https://apidoc.vidispine.com/latest/security/auth.html#automatic-creation-of-users (the
example in `test-environment` has this done). Now, the code is present in Vidispine the next time the server is loaded.

The easiest way to test is with Docker. You can find a sample Docker stack at https://github.com/fredex42/vs-containers.
Clone this and run `docker-compose up` to get a basic setup.

You can build a customised VS image by:
1. build the standard image from  https://github.com/fredex42/vs-containers and tag it
2. update the Dockerfile in `test-environment` to build FROM your tagged image
3. build the JAR and output it as `vidispine-auth-jar.jar` into the `test-environment` directory so that Docker can find it.
4. build the docker image in `test-environment` and tag it. This will contain the built JAR and shiro.ini files.
5. update docker-compose.yaml from https://github.com/fredex42/vs-containers to point to the tagged image from `test-environment.`
6. run `docker-compose up` to start up the stack

## How to get a bearer token to test it?

If you do not have an SSO environment set up to use, then you can use Keycloak.
Run the included script at `ext-services/run-keycloak.sh` and configure it as per https://www.keycloak.org/getting-started/getting-started-docker.

The script should configure a docker volume to persist your data across restarts.

Once you have configured a test user and realm and verified that you can sign in with them,
you can get a token by making a POST request to http://localhost:8090/auth/realms/vidispine/protocol/openid-connect/token with
a body in x-www-form-urlencoded format containing the following key/value pairs:
- username: {test user you set up}
- password:  {test user password you set up}
- client_id: {client id you set up}
- grant_type: password {the literal string "password", not the password you configured}

This should respond with a JSON object with your bearer token in the access_token field.

In order to verify the access token, you need the public key for the realm.
You can get this from the Keycloak admin console by going to the realm you configured,
clicking Realm Settings and clicking the Keys tab.
One of the options in the table is `RS256` and on the righthand side should have two buttons, Certificate and Public Key.
Click 'certificate' and copy-paste the string you see to your app configuration.

You can verify your bearer token at https://jwt.io.
1. Select RS256 algorithm
2. Paste your token into the left-hand box, then remove the Private Key from the right hand box.
3. Remove the private key data but keep the -----BEGIN and END lines. Replace 'RSA PUBLIC KEY' with 'CERTIFICATE' (so it reads BEGIN CERTIFICATE) and keep the dashes
4. Paste in the certificate that you got from keycloak
5. The system should decode the JWT content and verify the signature.  If the signature does not verify triple-check you copy/pasted
everything properly!

