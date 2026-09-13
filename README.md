# 1Fi Marketplace

A Jetpack Compose implementation of the **1Fi Marketplace** feature for the Shop page, built as part of the 1Fi SDE Intern assignment.

The Shop page has three tabs — **1Fi Marketplace**, **Top Brands**, and **Nearby Stores** — with Marketplace fully implemented: product listing, product detail, variant selection, EMI plan selection, and a Proceed CTA, backed by a mock data layer shaped like a real API.

## Screenshots

| Product Listing | Product Detail |
|---|---|
| _add screenshot_ | _add screenshot_ |

## Tech stack

- **Kotlin** + **Jetpack Compose** (Material 3)
- **MVVM** — `StateFlow` UI state, one-directional data flow
- **Hilt** — dependency injection
- **Navigation Compose** — nested nav graph for the Marketplace tab
- **Coil 3** — image loading
- **Retrofit** (scaffolded, not wired up — see [Architecture](#architecture))

## Getting started

1. Clone the repo and open it in Android Studio.
2. Sync Gradle.
3. Run the `app` module — `MainActivity` launches directly into the Shop page (Marketplace tab selected by default).

No backend or API keys required — the app runs entirely on mock data.

## Project structure

```
app/src/main/java/com/onefi/app/
├── MainActivity.kt / OneFiApplication.kt
├── core/                     # theme, shared components, utilities
└── feature/shop/
    ├── ShopTab.kt            # Marketplace / Top Brands / Nearby Stores
    ├── topbrands/            # placeholder screen
    ├── nearbystores/         # placeholder screen
    └── marketplace/
        ├── data/             # models, DTOs, mock + (future) remote data sources, repository
        ├── di/                # Hilt module
        ├── presentation/
        │   ├── list/          # product grid
        │   └── detail/        # product detail, variants, EMI plans, Proceed CTA
        └── navigation/        # nested NavHost for list <-> detail
```

## Architecture

MVVM + repository pattern, with a `Resource<T>` (Loading/Success/Error) wrapper so every screen handles loading/error/empty states consistently:

```
MockMarketplaceDataSource ──▶ MarketplaceRepositoryImpl ──▶ ViewModel ──▶ Composable
   (seeded catalog)             (maps DTO → domain,           (StateFlow)   (collectAsState)
                                  wraps in Resource<T>)
```

Product/EMI data is never hardcoded into a Composable — everything flows through the repository. The mock data source implements the same interface a `RemoteMarketplaceDataSource` (Retrofit, already scaffolded in `data/remote/`) would, so swapping in a real backend later is a one-line change in `MarketplaceModule`, with no changes needed to the ViewModels or UI.

## Notes

- `MainActivity` / `AndroidManifest.xml` exist only so this feature is independently runnable for review — in the real 1Fi app, `ShopScreen`/`MarketplaceSectionRoot` would be hosted inside the app's existing Activity and bottom navigation instead.
- Product images are placeholder URLs; swap `MockMarketplaceDataSource`'s `imageUrls` for real assets as needed.
