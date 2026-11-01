# BitChord Releases

Every push to `main`, including a merged pull request, runs the Android release workflow.

The workflow builds and publishes both Android phone variants to the GitHub Releases page:

| APK | Android application ID | Purpose |
|---|---|---|
| `BitChord-<version>.apk` | `com.music.bitchord` | Original BitChord |
| `BitChordFC-<version>.apk` | `com.spotify.music` | BitChord FC with the ColorOS Fluid Cloud identity |

Universal APKs can be installed on supported Android devices. ABI-specific APKs are also attached for smaller downloads. `SHA256SUMS.txt` contains checksums for the APK assets.

## Future release process

1. Merge a pull request into `main`.
2. GitHub Actions builds both variants.
3. The workflow creates a versioned GitHub Release and uploads the APKs.
4. Download BitChord FC from the `BitChordFC-` asset.

BitChord FC checks this repository's latest GitHub Release for updates. The original BitChord flavor keeps its upstream update channel.

[Open Releases](https://github.com/Batmancpu/Bitchordfc/releases) · [Open Android Release Workflow](https://github.com/Batmancpu/Bitchordfc/actions/workflows/android.yml)
