# BitChord FC Releases

Every push to `main`, including a merged pull request, starts one repository-wide serialized workflow: **Build, Test & Release**.

The workflow runs the phone tests, TV build, web checks and backend checks/deployment in sequence. Only after every required check succeeds does it build and publish the BitChord FC phone APK. It does not build the original BitChord package or a Wear OS APK.

| APK | Android application ID | Purpose |
|---|---|---|
| `BitChordFC-<version>.apk` | `com.spotify.music` | BitChord FC with the ColorOS Fluid Cloud identity |

The universal APK is the easiest installation choice. ABI-specific APKs are attached for smaller downloads. `SHA256SUMS.txt` contains checksums for the APK assets.

## Future release process

1. Merge a pull request into `main`.
2. The single serialized workflow runs all checks in order.
3. If all checks pass, GitHub creates a versioned Release and uploads BitChord FC.
4. Download the universal `BitChordFC-` APK from Releases.

BitChord FC checks this repository's latest GitHub Release for updates. Releases use increasing Android version codes and a persistent signing key so future updates can install over the previous version.

[Open Releases](https://github.com/Batmancpu/Bitchordfc/releases) · [Open Build, Test & Release workflow](https://github.com/Batmancpu/Bitchordfc/actions/workflows/check.yml)
