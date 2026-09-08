# LT Item Mail

Have you ever imagined mailing items to your friends in Minecraft?

Paper plugin that lets players **send items to each other by mail** — virtually or through placeable **mailbox blocks** (shulker boxes). Recipients get notified, open mail by command or at a physical mailbox, accept or deny packages, and admins can ban abusers, recover lost mail, and manage the system.

Originally by **Nobookie**; maintained in this repository.

| | |
|-|-|
| **SpigotMC** | [Resource page](https://www.spigotmc.org/resources/62294/) |
| **Bukkit** | [Dev page](https://dev.bukkit.org/projects/lt-item-mail) |
| **Javadocs** | [API docs](https://javadocs.nobookie.net.br/LTItemMail/) |
| **Dev builds** | [Jenkins](https://jenkins.nobookie.net.br/job/LTItemMail/) |
| **License** | GPL-3.0 |

---

## Features

* Send item packages to any player with `/mailitem <player> [label]`
* Optional **physical mailbox blocks** (craftable shulker-box mailboxes)
* Virtual inbox: list, open, delete mail by ID
* Mail statuses: pending → accepted / denied
* Optional **economy cost** per mail or per item (Vault / The New Economy)
* Ban / unban players from using the mail system
* SQLite (default) or shared **MySQL** storage
* Optional **BungeeCord / multi-server** mode (shared MySQL + unique server IDs)
* Languages: English, Portuguese, Vietnamese
* Soft hooks: WorldGuard, GriefPrevention, RedProtect, Towny, dynmap, BlueMap, DecentHolograms, PlaceholderAPI, Citizens, HeadDatabase, Skulls, PlugMan
* Public **Java API** for other plugins (`sendSpecialMail`, mailbox block queries, events)
* New-mail notifications: chat, title, or toast

---

## Requirements

| Dependency | Required? | Notes |
|-|-|-|
| Paper (api-version **26.2** / Java **25** in current build) | Yes | See `plugin.yml` / `pom.xml` for the exact target |
| Vault or The New Economy | Soft | Only if `hook.economy.enable` is true |
| MySQL | Soft | Required for multi-server / Bungee mode |

Other listed soft-depends are optional and only activate when present and enabled in config.

---

## Installation

1. Build the plugin (see [Building](#building)) or download a release / Jenkins build.
2. Drop the jar into each backend server’s `plugins/` folder.
3. Start the server once to generate `plugins/LTItemMail/config.yml` and language files.
4. Edit the config (database, economy, language, etc.).
5. Restart or run `/itemmailadmin reload`.

### Multi-server (Bungee / Velocity backends)

1. Install LT Item Mail on **every backend** (not on the proxy).
2. Set `plugin.bungee-mode: true`.
3. Give each backend a **unique** `plugin.bungee-server-id` (e.g. `lobby`, `survival`).
4. Point all backends at the **same MySQL** database under `database.mysql`.
5. Changing `bungee-server-id` later invalidates existing physical mailbox blocks on that server — players must replace them.

---

## Building

```bash
git clone https://github.com/dpembo/LTItemMail.git
cd LTItemMail
mvn clean package
```

Requires **Maven** and **JDK 25** (see `pom.xml` `release`).

Output jar is produced under `target/` (assembly jar-with-dependencies, final name `LTItemMail`).

---

## Quick start (players)

1. **Send mail**
   ```text
   /mailitem Steve Happy birthday!
   ```
   A send GUI opens. Put items in the inventory and confirm/send through the UI.

2. **Recipient** is notified (chat / title / toast depending on config).

3. **Open mail**
   ```text
   /itemmail list
   /itemmail open <id>
   ```
   Or walk up to a placed **mailbox block** and use it (if they have one).

4. Accept or deny the package in the open mail GUI. Accepted items go to the player; denied mail is closed according to plugin rules.

5. **Craft a mailbox** (default recipe) if physical boxes are allowed, then place it:
   ```text
   p p p
   p b p
   p g p
   ```
   - `p` = stone  
   - `b` = chest  
   - `g` = iron ingot  

   Result: a special white shulker box marked as a mailbox. Place it in the world; others can interact under permission and protection rules.

---

## Commands

Aliases are listed in parentheses.

### Player

| Command | Permission | Default | Description |
|-|-|-|-|
| `/itemmail` (`ima`, `imail`) | `ltitemmail.player` | true | Root command; with no args opens first pending mail or shows help |
| `/itemmail help` | `ltitemmail.player` | true | List player commands |
| `/itemmail version` | `ltitemmail.player.version` | true | Plugin version, build number, authors |
| `/itemmail list` | `ltitemmail.player.list` | true | List your mail (IDs, status, sender, label) |
| `/itemmail open <id>` | `ltitemmail.player.open` | true | Open a mail by numeric ID |
| `/itemmail delete <id>` | `ltitemmail.player.delete` | true | Delete one of your mails |
| `/itemmail price` | `ltitemmail.player.price` | true | Show send cost (if economy is enabled) |
| `/itemmail info` | `ltitemmail.player.info` | true | Your registry date, ban status, sent/received counts |
| `/itemmail color <color>` | `ltitemmail.player.color` | true | Recolor the mailbox item in hand (shulker colors) |
| `/itemmail blocks` | `ltitemmail.player.blocks` | true | List coordinates of your placed mailbox blocks |
| `/mailitem <player> [label…]` (`mit`, `enviaritem`) | `ltitemmail.player.send` | true | Open the send GUI for that player (optional label) |

### Admin

| Command | Permission | Default | Description |
|-|-|-|-|
| `/itemmailadmin` (`imad`, `imadmin`) | `ltitemmail.admin` | op | Root admin command / help |
| `/itemmailadmin help` | `ltitemmail.admin` | op | List admin commands |
| `/itemmailadmin update [-s]` | `ltitemmail.admin.update` | op | Check for newer builds (`-s` = silent if up to date) |
| `/itemmailadmin list <player>` | `ltitemmail.admin.list` | op | List a player’s deleted/opened mail history |
| `/itemmailadmin recover <id>` | `ltitemmail.admin.recover` | op | Open deleted mail that still has items (recovery) |
| `/itemmailadmin reload` | `ltitemmail.admin.reload` | op | Reload configuration and related modules |
| `/itemmailadmin ban <player> [reason…]` | `ltitemmail.admin.ban` | op | Ban a player from using the mail system |
| `/itemmailadmin unban <player>` | `ltitemmail.admin.unban` | op | Remove a mail ban |
| `/itemmailadmin banlist` | `ltitemmail.admin.banlist` | op | List banned players |
| `/itemmailadmin info <player>` | `ltitemmail.admin.info` | op | Stats for another player |
| `/itemmailadmin blocks <player>` | `ltitemmail.admin.blocks` | op | List that player’s mailbox block locations |
| `/itemmailadmin dump` | `ltitemmail.admin.dump` | op | Dump server/plugin/extension versions (support) |
| `/itemmailadmin changelog` | `ltitemmail.admin.changelog` | op | Show changelog for the current build |
| `/itemmailadmin give <player>` | `ltitemmail.admin.give` | op | Give a mailbox item to a player |
| `/itemmailwipe [confirm]` | `ltitemmail.admin.wipe` | op | **Wipe the entire mail database** (destructive; requires `confirm`) |

Self-mail is blocked unless the sender has `ltitemmail.admin.bypass` and uses `/mailitem <self> --bypass`.

---

## Permissions

All nodes are registered by the plugin. Defaults:

### Player (default: true)

| Node | Purpose |
|-|-|
| `ltitemmail.player` | Base player commands |
| `ltitemmail.player.version` | `/itemmail version` |
| `ltitemmail.player.list` | `/itemmail list` |
| `ltitemmail.player.open` | `/itemmail open` |
| `ltitemmail.player.delete` | `/itemmail delete` |
| `ltitemmail.player.send` | `/mailitem` |
| `ltitemmail.player.price` | `/itemmail price` |
| `ltitemmail.player.info` | `/itemmail info` |
| `ltitemmail.player.color` | `/itemmail color` |
| `ltitemmail.player.blocks` | `/itemmail blocks` |
| `ltitemmail.player.notify` | Receive new-mail notifications |
| `ltitemmail.block.place` | Place mailbox blocks |
| `ltitemmail.block.break` | Break own mailbox blocks |
| `ltitemmail.block.use` | Use / interact with mailbox blocks |

### Admin (default: op)

| Node | Purpose |
|-|-|
| `ltitemmail.admin` | Base admin commands |
| `ltitemmail.admin.update` | Update check |
| `ltitemmail.admin.list` | List another player’s mail |
| `ltitemmail.admin.recover` | Recover deleted mail |
| `ltitemmail.admin.ban` | Ban from mail |
| `ltitemmail.admin.unban` | Unban |
| `ltitemmail.admin.banlist` | Ban list |
| `ltitemmail.admin.info` | Info on others |
| `ltitemmail.admin.reload` | Reload |
| `ltitemmail.admin.blocks` | List others’ blocks |
| `ltitemmail.admin.notify` | Admin notifications |
| `ltitemmail.admin.bypass` | Bypass self-mail restriction |
| `ltitemmail.admin.wipe` | Wipe database |
| `ltitemmail.admin.dump` | Debug dump |
| `ltitemmail.admin.changelog` | Changelog |
| `ltitemmail.admin.give` | Give mailbox item |
| `ltitemmail.block.break.bypass` | Break others’ mailbox blocks |

If **Vault** is present, permission checks can go through Vault’s permission provider.

---

## Configuration overview

Main file: `plugins/LTItemMail/config.yml`.

### Plugin

| Key | Default | Description |
|-|-|-|
| `plugin.enable` | `true` | Master enable switch |
| `plugin.language` | `english` | `english`, `portuguese`, or `vietnamese` |
| `plugin.tag` | `&6[LTIM]` | Chat prefix |
| `plugin.bungee-mode` | `false` | Multi-server mode |
| `plugin.bungee-server-id` | `server1` | Unique ID per backend (do not change lightly) |
| `plugin.resource-pack-download` | `false` | Download/update resource pack on startup |
| `plugin.debug` | `false` | Extra debug logging |

### Database

| Key | Description |
|-|-|
| `database.type` | `sqlite` or `mysql` |
| `database.convert` | Import from previous storage type when switching |
| `database.sqlite.file` | SQLite filename (default `mailboxes.db`) |
| `database.mysql.*` | Host, user, password, database, pool settings |

### Economy

| Key | Description |
|-|-|
| `hook.economy.enable` | Charge for sending mail |
| `hook.economy.type` | `Vault` or e.g. `TheNewEconomy:USD` |
| `mail.cost.per-item` | If true, cost × number of items; else flat per mail |
| `mail.cost.value` | Price (default `30.0` flat or `1.5` style per-item depending on mode) |

### Mail UX

| Key | Description |
|-|-|
| `mail.display` | `CHAT`, `TITLE`, or `TOAST` for new-mail alerts |
| `mail.textures` | `true` if using the LT Item Mail resource pack |
| `mail.name` | Display name of the mail inventory |

### Protection / map hooks

Toggle under `hook.*`: dynmap, bluemap, decentholograms, griefprevention, redprotect, towny (+ optional tax per mailbox), worldguard, headdatabase, skulls, citizens.

### Autorun

`autorun.mail.*` can run console/player commands when mail is closed, accepted, or denied (e.g. re-open the list UI).

### Updates

| Key | Description |
|-|-|
| `update.check` | Check for new builds |
| `update.periodic-notification` | Remind ops periodically |
| `update.automatic` | Download new jar into the update folder |

---

## Examples

**Send a labelled package**

```text
/mailitem Alex Gift from the end event
```

**Check inbox and open mail #12**

```text
/itemmail list
/itemmail open 12
```

**See send price**

```text
/itemmail price
```

**Ban a mail abuser**

```text
/itemmailadmin ban Griefer42 Spamming empty mails
```

**Give a mailbox item and list blocks**

```text
/itemmailadmin give Steve
/itemmail blocks
```

**Wipe all mail data (irreversible)**

```text
/itemmailwipe
/itemmailwipe confirm
```

---

## PlaceholderAPI

With PlaceholderAPI installed, expansion identifier is `ltitemmail` (plugin name lowercased). Examples:

| Placeholder | Meaning |
|-|-|
| `%ltitemmail_player_mailsent%` | Mails sent count |
| `%ltitemmail_player_mailreceived%` | Mails received count |
| `%ltitemmail_player_banned%` | Banned yes/no (localised) |
| `%ltitemmail_player_banreason%` | Ban reason |
| `%ltitemmail_player_registered%` | Registry date |
| `%ltitemmail_player_pending_total%` | Pending mail count |
| `%ltitemmail_player_accepted_total%` | Accepted mail count |
| `%ltitemmail_player_denied_total%` | Denied mail count |

Further `player_<status>_…` forms exist for indexed access; see `LTPlaceholderAPI` in source for the full set.

---

## Developer API

Other plugins can hook LT Item Mail:

```java
LTItemMailAPI api = LTItemMailAPI.get(yourPlugin);

// Anonymous / console “special” mail
api.sendSpecialMail(LTPlayer.fromName("Steve"), items, "Reward");

// Query placed mailbox blocks (this server or by owner / server id)
List<MailboxBlock> blocks = api.getMailboxBlockList();
```

### Maven

```xml
<repository>
  <id>gmj-repository</id>
  <name>Maven G.M.J</name>
  <url>https://maven.nobookie.net.br/releases</url>
</repository>

<dependency>
  <groupId>br.net.gmj.nobookie</groupId>
  <artifactId>LTItemMail</artifactId>
  <version>1.1.1</version>
  <scope>provided</scope>
</dependency>
```

Version numbers on Maven may differ from this fork’s `pom.xml`; use the artifact version you target.

### Events (examples)

* `PlayerSendMailEvent` / `ServerSendMailEvent`
* `PlayerPlaceMailboxBlockEvent` / `PlaceMailboxBlockEvent`
* `PlayerBreakMailboxBlockEvent` / `BreakMailboxBlockEvent`

Javadocs: https://javadocs.nobookie.net.br/LTItemMail/

---

## Behaviour notes

* Mail is stored in the database (SQLite file under the plugin folder, or MySQL).
* Sending opens an **OUT** inventory GUI; receiving uses **IN** / **IN_PENDING** GUIs.
* Physical mailboxes are special shulker boxes with plugin PDC data — not normal shulkers.
* Built-in block protection can be combined with WorldGuard, GriefPrevention, RedProtect, or Towny.
* In Bungee mode, at least one player may need to be online on a backend for some cross-server plugin messages (e.g. give mailbox / special mail notify).
* `/itemmailwipe` purges data; restart afterwards as instructed by the plugin messages.

---

## Troubleshooting

| Symptom | What to check |
|-|-|
| Cannot send mail | `ltitemmail.player.send`; not banned; economy balance if cost enabled |
| No notification | `ltitemmail.player.notify`; `mail.display`; language file |
| Economy not charging | `hook.economy.enable`, Vault/TNE installed, `mail.cost.*` |
| Physical mailbox “breaks” after config change | `bungee-server-id` changed → replace blocks |
| Multi-server mail missing | Same MySQL on all backends; `bungee-mode: true`; unique server IDs |
| Recover fails | Mail must be deleted, not denied, and still contain items |
| Permission always denied with Vault | Vault permission provider linked to your perm plugin |

Enable `plugin.debug: true` temporarily for extra console detail.

---

## License

This project is licensed under the **GNU General Public License v3.0**.  
See [LICENSE](LICENSE) in this repository.

---

## Authors

* **Nobookie** (original)
* Maintained in this repository by **dpembo** / Globeworks contributors as applicable

