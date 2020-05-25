## What is it?

## What is it meant to do?

## How do I use it?

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

