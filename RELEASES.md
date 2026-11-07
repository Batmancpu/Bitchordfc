# BitChord FC — Fluid Cloud

This fork follows upstream BitChord and adds one phone application variant for ColorOS Fluid Cloud.

## Update pipeline

1. A push or merged pull request to `main` runs Android phone unit tests.
2. When tests pass on `main`, GitHub builds only the `SpotifyRelease` phone variant.
3. The workflow verifies the package ID and APK signature, compares the signing certificate fingerprint with the previous release, and blocks publication if the signer changes; it then publishes one universal APK plus a SHA-256 checksum.
4. Obtainium tracks the repository's GitHub Releases and installs newer APKs.

| Setting | Value |
|---|---|
| App label | BitChord FC |
| Package ID | `com.spotify.music` |
| APK asset | `BitChordFC-<version>.apk` |
| Releases | [Batmancpu/Bitchordfc](https://github.com/Batmancpu/Bitchordfc/releases) |

## Obtainium setup

Add `https://github.com/Batmancpu/Bitchordfc` in Obtainium using its GitHub Releases source. Set an asset filter matching `BitChordFC-.*\.apk$` if Obtainium lists unrelated assets.

## One-time signing setup

The existing `v1.9.8` APK was signed with a one-off CI key which is no longer available. Android requires the same signing certificate to install updates in place. **Uninstall that build once before installing the first release signed with your permanent key.** Back up local app data first if necessary.

Create one keystore on a trusted computer and keep it in secure storage. Add these under **Settings → Secrets and variables → Actions**:

- `KEYSTORE_BASE64`: Base64-encoded keystore
- `KEYSTORE_PASSWORD`: Keystore password
- `KEY_ALIAS`: key alias
- `KEY_PASSWORD`: private-key password

Example (run once; never commit the keystore or passwords):

```bash
keytool -genkeypair -v -keystore bitchord-fc-release.jks -storetype JKS -storepass 'CHOOSE_A_LONG_PASSWORD' -keypass 'CHOOSE_A_LONG_PASSWORD' -alias bitchordfc -keyalg RSA -keysize 2048 -validity 10000 -dname "CN=BitChord FC"
base64 -w 0 bitchord-fc-release.jks
```

Do not replace or regenerate the key after publishing. Each new release records its signing certificate SHA-256 fingerprint. Subsequent builds compare their signer with that recorded fingerprint and fail closed on any mismatch; they also fail if the previous release cannot be checked. If secrets are absent or invalid, no APK is published.

## Versioning

The last fork release is `1.9.8`. Future versions use `1.9.<GitHub Actions run number>`; Android version codes are above the previous fork build.
