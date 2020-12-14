package com.example.khmer_keyboard;

import android.inputmethodservice.InputMethodService;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class KhmerKeyboard extends InputMethodService {

    StringBuffer inputString;
    ViewGroup suggestionRow;
    boolean isAutoComplete = true;
    InputConnection ic;
    int curPos;
    View view;
    private RecyclerView recyclerView;
    MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;



    public KhmerKeyboard() {
    }



    String[] emo_smiley = {"😀","😃","😄","😁","😆","😅","😂","🤣","😭","😗",
            "😙","😚","😘","🥰","😍","🤩","🥳","🤗","🙃",
            "🙂","☺️","😊","😏","😌","😉","🤭","😶","😐","😑",
            "😔","😋","😛","😝","😜","🤪","🤔","🤨","🧐","🙄","😒","😤",
            "😠","😡","🤬","☹️","🙁","😕","😟","🥺","😬","😳","🤐","🤫","😰",
            "😨","😧","😦","😮","😯","😲","😱","🤯","😢","😥","😓","😞","😖","😣","😩","😫","😵","🤤","🥱","😴","😪","🌛","🌜","🌚","🌝","🌞","🤢","🤮",
            "🤧","😷","🤒","🤕","🥴","🥵","🥶","😈","👿","😇","🤠","🤑","😎","🤓","🤥","🤡","👻","💩","👽","🤖","🎃","👹",
            "☠️","😺","😸","😹","😻","😼","😽","🙀","😿","😾","❤️","🧡","💛","💚","💙","💜",
            "🤎","🖤","🤍","♥️","💘","💝","💖","💗","💓","💞","💕","💌","💟","❣️","💔","💋",
            "🔥","💫","⭐️","🌟","✨","⚡️","💥","💯","💢","💨","💦","💤","🕳","👥","👤",
            "🗣","🧠","🩸","🦠","🦷","🦴","💀","👀","👁","👄","👅","👃","👂","🦻","🦶","🦵",
            "🦿","🦾","💪","👍","👎","👏","🙌","👐","🤲","🤝","🤜","🤛","✊","👊","🖐","✋","🤚","👋","🤏","👌","✌️","🤘","🤟","🤙","🤞","🖕","🖖","☝️","👆","👇","👉","👈","✍️","🤳"};

    String[] emo_people = {"🙇","🙋","💁","🙆","🙅","🤷","🤦","🙍","🙎","🧏","💆","💇","🧖",
            "🛀","🛌","🧘","🧑‍🦯","🧑‍🦼","🧑‍🦽","🧎","🧍","🚶","🏃","🤸","🏋️","⛹️","🤾","🚴","🚵","🧗","🤼","🤹","🏌️","🏇","🤺","⛷","🏂","🪂","🏄","🚣",
            "🏊","🤽","🧜","🧚","🧞","🧝","🧙","🧛","🧟","🦸","🦹","🤶","👼","💂","👸","🤴","🤵","👰","🧑‍🚀","👷","👮","🕵️","🧑‍✈‍","🧑‍🔬","🧑‍⚕‍","🧑‍🔧","🧑‍🏭",
            "🧑‍🚒","🧑‍🌾","🧑‍🏫","🧑‍🎓","🧑‍💼","🧑‍⚖‍","🧑‍💻","🧑‍🎤","🧑‍🎨","🧑‍🍳","👳","🧕","👲","👶","🧒","🧑","🧓","🧑‍🦳","🧑‍🦰","👱","🧑‍🦱","🧑‍🦲","🧔","🕴","💃","🕺","👯",
            "👭","👬","👫","💏","💑","🤰","🤱","👪"} ;

    String[] emo_nature = {"💐","🌹","🥀","🌷","🌺","🌸","🏵","🌻","🌼","💮","🍂","🍁","🌾",
            "🌱","🌿","🍃","☘️","🍀","🌵","🌴","🌳","🌲",
            "🏞","🌊","🌬","🌀","🌁","🌫","🌪","⛄️","❄️","🏔","🌡","🔥","🌋","🏜","🏖","⛱","🌇","🌅","🌄",
            "☀️","🌤","⛅️","🌥","🌦","☁️","🌨","⛈","🌩","🌧","💧","☔️","⚡️","🌈",
            "⭐️","🌟","💫","✨","☄️","🌠","🌌","🌉","🌆","🌃","🌍","🌎","🌏","🌑","🌒","🌓","🌔","🌕","🌖","🌗","🌘","🌙","🪐",
            "🙈","🙉","🙊","🐵","🦁","🐯","🐱","🐶","🐺","🐻","🐨","🐼","🐹","🐭","🐰","🦊","🦝","🐮","🐷","🐽","🐗","🦓","🦄","🐴",
            "🐸","🐲","🦎","🐉","🦖","🦕","🐢","🐊","🐍",
            "🐁","🐀","🐇","🐈","🐩","🐕","🦮","🐕‍🦺","🐅","🐆","🐎","🐖","🐄","🐂","🐃","🐏","🐑","🐐","🦌","🦙","🦥","🦘",
            "🐘","🦏","🦛","🦒","🐒","🦍","🦧","🐪","🐫","🐿","🦨","🦡","🦔","🦦","🦇",
            "🐓","🐔","🐣","🐤","🐥","🐦","🦉","🦅","🦜","🕊","🦢","🦩","🦚🦃","🦆","🐧",
            "🦈","🐬","🐋","🐳","🐟","🐠","🐡","🦐","🦞","🦀","🦑","🐙","🦪",
            "🦂","🕷","🕸","🐚","🐌","🐜","🦗","🦟","🐝","🐞","🦋","🐛","🦠","🐾"};
    String[] emo_food = {"🍓","🍒","🍎","🍉","🍑","🍊","🥭","🍍",
            "🍌","🍋","🍈","🍏","🍐","🥝","🍇","🥥",
            "🍅","🌶️", "🍄","🥕","🍠","🧅","🌽","🥦",
            "🥒","🥬","🥑","🍆","🧄","🥔","🌰","🥜",
            "🍞","🥐","🥖", "🥯","🍖","🍗","🥩","🥓",
            "🧀","🥚","🍳","🥞","🧇","🍔","🌭","🥪",
            "🥨","🍟","🍕","🌮", "🌯","🥙","🍜","🍛",
            "🍲","🥗","🥣","🥫","🍝","🥘","🧆","🍣",
            "🍤","🥡","🍚","🍱","🥟", "🍢","🍦","🍨",
            "🍧","🥮","🥠","🍡","🍥","🍘","🍙","🥧",
            "🍰","🍮","🎂","🧁","🍭","🍬","🍫", "🍩",
            "🍪","🍯","🧂","🧈","🍿","🧊","🥤","🧃",
            "🥛","🍷","🍾","🥂","🍻","🍺","🧉","🍵",
            "🍼","🥃","🍸","🍹","🍶","🥢","🍴","🥄",
            "🔪","🍽️"};

    String[] emo_transport = {"🛑","🚧","🚥","🚦","🚨","⛽","🛢️","🧭",
            "⚓","🏍️","🛵","🚲","🦼","🦽","🛴","🛹",
            "🚇","🚏","🚙","🚗","🚐","🚚","🚛","🚜",
            "🏎️","🚒","🚑","🚓","🚕","🛺","🚌","🚈",
            "🚝","🚅","🚄","🚂","🚘","🚔","🚍","🚉",
            "🚊","🚞","🚎","🚋","🚃","🚖","🚆","🚢",
            "🛳️","🛥️","🚤","⛴","⛵","🛶","🛫","✈",
            "🛩️","🚀","🛸","🚁","🚡","🚠","🚟","🛬",
            "🎢","🎡","🎠","🎪","🗼","🗽","🗿","💈","💒",
            "⛪","🛕","🕋","🕌","🕍","⛩","⛲","🏛️",
            "🏩","🏯","🏰","🏗️","🏢","🏭","🏬","🏪",
            "🏟️","🏡","🏠","🏚️","🏥","🏤","🏣","🏨",
            "🏫","🏦","🏘️","⛺","🏕️","🌅","🌄","🌇",
            "🌁","🏙️","🌆","🏜️","🏞️","🗻","🌋","⛰",
            "🏔️","🌉","🌌","🌃","🏖️","⛱","🏝️","🛤️",
            "🛣️","🗺️","🗾","🌐","💺","🧳"};

    String[] emo_activities = {"🎉","🎊","🎈","🎂","🎀","🎁","🎇","🎆","🧨","🎄","🎋","🎍","🎑","🎎","🎏","🎐","🪔","🧧",
            "🎃","🎗","🥇","🥈","🥉","🏅","🎖","🏆","📢","🥅","⚽","⚾","🥎","🏀","🏐","🏈","🏉","🎾",
            "🏸","🥍","🏏","🏑","🏒","🥌","🛷","🎿","⛸","🩰","⛳", "🎯","🏹","🥏","🪁","🎣","🤿","🩱",
            "🎽","🥋","🥊","🎱","🏓","🎳","♟","🪀","🧩","🎮","🕹","👾","🔫","🎲","🎰","🎴","🀄️","🃏","🎩",
            "📷","📸","🖼","🎨","🖌","🖍","🧵","🧶","🎼","🎵","🎶","🎹","🎷","🎺","🎸","🪕","🎻","🥁",
            "🎤","🎧", "🎚","🎛","🎙","📻","📺","📼","📹","📽","🎥","🎞","🎬","🎭","🎫","🎟"};

    String[] emo_object = {"📱", "📲", "☎", "📞", "📟", "📠", "🔌", "🔋", "🖲️", "💽", "💾", "💿", "📀", "🖥️", "💻", "⌨", "🖨️", "🖱️", "🏧",
            "💸", "💵", "💴", "💶", "💷", "💳", "💰", "🧾", "🧮", "⚖", "🛒", "🛍️", "🕯️", "💡", "🔦", "🏮", "🧱", "🚪", "🪑",
            "🛏️", "🛋️", "🚿", "🛁", "🚽", "🧻", "🧸", "🧷", "🧹", "🧴", "🧽", "🧼", "🪒", "🧺", "🧦", "🧤", "🧣", "👖",
            "👕", "🎽", "👚", "👔", "👗", "👘", "🥻", "🩱", "👙", "🩳", "🩲", "🧥", "🥼", "👛", "⛑", "🎓", "🎩", "👒", "🧢",
            "👑", "🎒", "👝", "👛", "👜", "💼", "🧳", "☂", "🌂", "💍", "💎", "💄", "👠", "👟", "👞", "🥿", "👡", "👢",
            "🥾", "👓", "🕶️", "🦯", "🥽", "⚗", "🧫", "🧪", "🌡️", "🧬", "💉", "💊", "🩹", "🩺", "🔬", "🔭", "📡", "🛰️",
            "🧯", "🪓", "🧲", "🧰", "🗜️", "🔩", "🔧", "🔨", "⚒", "🛠️", "⛏", "⚙", "🔗", "⛓", "📎", "🖇️", "📏", "📐", "✂",
            "📌", "📍", "🗑️", "🖌️", "🖍️", "🖊️", "🖋️", "✒", "✏", "📝", "📒", "📔", "📕", "📓", "📗", "📘", "📙", "📚", "📖",
            "🔖", "🗒️", "📄", "📃", "📋", "📇", "📑", "🗃️", "🗄️", "🗂️", "📂", "📁", "📰", "🗞️", "📊", "📈", " 📉", "📦", "📫",
            "📪", "📬", "📭", "📮", "✉", "📧", "📩", "📨", "💌", "📤", "📥", "🗳️", "🏷️", "⌛", "⏳", "🕰️", "🕛", "🕧",
            "🕐", "🕜", "🕑", "🕝", "🕒", "🕞", "🕓", "🕟", "🕔", "🕠", "🕕", "🕡", "🕖", "🕢", "🕗", "🕣", "🕘", "🕤",
            "🕙", "🕥", "🕚", "🕦", "⏱", "⌚", "⏲", "⏰", "🗓️", "📅", "🛎️", "🛎️", "🔔", "📯", "📢", "📣", "🔍", "🔎",
            "🔮", "🧿", "📿", "🏺", "⚱", "⚰", "🚬", "💣", "📜", "⚔", "🗡️", "🛡️", "🗝️", "🔑", "🔐", "🔏", "🔓", "🔒","❤️","🧡","💛","💚","💙","💜","🤎","🖤","🤍","🔴","🟠","🟡","🟢","🔵","🟣","🟤","⚫️","⚪️","🟥","🟧"
            };
    String[] emo_object1 ={"🟨","🟩","🟦","🟪","🟫","⬛️","⬜️","♈️","♉️","♊️","♋️","♌","♍",
            "♏","♐️","♑️","♒️","♓️","⛎","♀️","♂️","🔻","🔺","❕","❗️","❔","❓","⁉️","‼️","⭕️",
            "❌","🚫","🚳","🚭","🚯","🚱","🚷","📵","🔞","🔕","🔇","🅰️","🆎","🅱️","🆑","🅾️","🆘","🛑","⛔️","📛","♨️","🉐",
            "㊙️","㊗️","🈴","🈵","🈹","🈲","🉑","🈶","🈚️","🈸","🈺","🈷️","🔶","🔸","✴️","🆚","🎦","📶","🔁","🔂","🔀","▶️",
            "⏩","⏭️","⏯️","◀️","⏪","⏮️","🔼","⏫","🔽","⏬","⏸️","⏹️","⏺️","⏏️","🔆","🔅","📲","📳","📴","🔈","🔉",
            "🔊","🎵","🎶","🎼","☢️","☣️","⚠️","🚸","⚜️","🔱","〽️","🔰","✳️","❇️","♻️","💱","💲","💹","🈯️","❎","✅",
            "✔️","☑️","⬆️","↗️","➡️","↘️","⬇️","↙️","⬅️","↖️","↕️","↔️","↩️","↪️","⤴️","⤵️","🔃",
            "🔄","🔙","🔛","🔝","🔚","🔜","🆕","🆓","🆙","🆗","🆒","🆖","🈁","🈂️","🈳","🔣","🔤","🔠","🔡","🔢","#️⃣","*️⃣","0️⃣","1️⃣",
            "2️⃣","3️⃣","4️⃣","5️⃣","6️⃣","7️⃣","8️⃣","9️⃣","🔟","🏧","⚕️","💠","🔷","🔹","🌐","Ⓜ️","ℹ️","🅿️","🚾","🗣️","👤","👥","👣",
            "🐾","🚻","🚹","♿️","🚼","🚮","🚰","🛂","🛃","🛄","🛅","👁️‍🗨️","💟","🛐","🕉️","☸️","☮️","☯️","✝️","✝️","☦️",
            "✡️","🔯","🕎","♾️","🆔","©️","®️","™️","✖️","➕","➖","➗","➰","➿","〰️","♥️","♦️","♣️","♠️","🔳",
            "◼️","◾️","▪️","🔲","◻️","◽️","▫️","💭","🗯️","💬","🗨️","🔘","🏳️","🏳️‍🌈"};
    String[] emo_flag ={"🇩🇿","🇦🇶","🇦🇺","🇧🇩","🇧🇯","🇧🇼","🇧🇬","🇨🇦","🇨🇫","🇨🇨","🇨🇰","🇨🇼","🇩🇲","🇬🇶","🇪🇺","🇫🇷","🇬🇲","🇬🇷","🇬🇹","🇭🇹","🇮🇳","🇮🇲","🎌","🇰🇮","🇱🇻","🇱🇮","🇲🇼","🇲🇭","🇲🇽","🇲🇪","🇳🇦","🇳🇿","🇳🇫","🇴🇲","🇵🇬","🇵🇱","🇷🇴","🇸🇹","🇸🇱","🇬🇸","🇸🇸","🇰🇳","🇸🇷","🇹🇯","🇹🇰","🇹🇲","🇦🇪","🇺🇸","🇻🇦","🇾🇪","🏴","🇺🇳","🇦🇸","🇦🇬","🇦🇹","🇧🇧","🇧🇲","🇧🇷","🇧🇫","🇮🇨","🇹🇩","🇨🇴","🇨🇷","🇨🇾","🇩🇴","🇪🇷","🇫🇰","🇬🇫","🇬🇪","🇬🇱","🇬🇬","🇭🇳","🇮🇩","🇮🇱","🇯🇪","🇽🇰","🇱🇧","🇱🇹","🇲🇾","🇲🇶","🇫🇲","🇲🇸","🇳🇷","🇳🇮","🇰🇵","🇵🇰","🇵🇾","🇵🇹","🇷🇺","🇸🇦","🇸🇬","🇸🇧","🇪🇸","🇱🇨","🇸🇪","🇹🇿","🇹🇴","🇹🇨",
            "🇬🇧","🇺🇾","🇻🇪","🇿🇲","🏴‍☠️","🇦🇫","🇦🇩","🇦🇷","🇦🇿","🇧🇾","🇧🇹","🇮🇴","🇧🇮","🇨🇻","🇨🇱","🇰🇲","🇨🇮","🇨🇿","🇪🇪","🇫🇴","🇵🇫","🇩🇪","🇬🇩","🇬🇳","🇭🇰","🇮🇷","🇮🇹","🇯🇴","🇰🇼","🇱🇸","🇱🇺","🇲🇻","🇲🇷","🇲🇩","🇲🇦","🇳🇵","🇳🇪","🇲🇰","🇵🇼","🇵🇪","🇵🇷","🇷🇼","🇸🇳","🇸🇽","🇸🇴","🇱🇰","🇵🇲","🇨🇭","🇹🇭","🇹🇹","🇹🇻","🏴󠁧󠁢󠁥󠁮󠁧󠁿","🇻🇮","🇻🇳","🇿🇼","🏁","🇦🇽","🇦🇴","🇦🇲","🇧🇸","🇧🇪","🇧🇴","🇻🇬","🇰🇭","🇧🇶","🇨🇳","🇨🇬","🇭🇷","🇩🇰","🇪🇬","🇸🇿","🇫🇯","🇹🇫","🇬🇭","🇬🇵","🇬🇼","🇭🇺","🇮🇶","🇯🇲","🇰🇿","🇰🇬","🇱🇷","🇲🇴","🇲🇱","🇲🇺","🇲🇨","🇲🇿","🇳🇱","🇳🇬","🇲🇵","🇵🇸","🇵🇭","🇶🇦","🇼🇸","🇷🇸","🇸🇰","🇿🇦","🇧🇱","🇻🇨","🇸🇾",
            "🇹🇱","🇹🇳","🇺🇬","🏴","🇺🇿","🇼🇫","🚩","🇦🇱","🇦🇮","🇦🇼","🇧🇭","🇧🇿","🇧🇦","🇧🇳","🇨🇲","🇰🇾","🇨🇽","🇨🇩","🇨🇺","🇩🇯","🇸🇻","🇪🇹","🇫🇮","🇬🇦","🇬🇮","🇬🇺","🇬🇾","🇮🇸","🇮🇪","🇯🇵","🇰🇪","🇱🇦","🇱🇾","🇲🇬","🇲🇹","🇾🇹","🇲🇳","🇲🇲","🇳🇨","🇳🇺","🇳🇴","🇵🇦","🇵🇳","🇷🇪","🇸🇲","🇸🇨","🇸🇮","🇰🇷","🇸🇭","🇸🇩","🇹🇼","🇹🇬","🇹🇷","🇺🇦","🏴","🇻🇺","🇪🇭"};


    String[] charAll = {"1","១","2","២","3","៣","4","៤","5","៥","6","៦","7","៧","8","៨","9","៩","១","០","ឦ","ឥ","ឪ","ឲ",
            "ឈ","ឆ","ឺ","ឹ","ែ","េ","ឬ","រ","ទ","ត","ួ","យ","ូ","ុ","ី","ិ","ៅ","ោ","ភ","ផ","ឿ","ៀ","ឧ","ឪ",
            "ាំ","ា","ៃ","ស","ឌ","ដ","ធ","ថ","អ","ង","ះ","ហ","ញ","​​​្","គ","ក","ឡ","ល","ោះ","ើ","​​៉","់","ឭ","ឮ",
            "ឍ","ឋ","ឃ","ខ","ជ","ច","េះ","វ","ព","ប","ណ","ន","ំ","ម","ុះ","ុំ","។","៕","?","​​៊"};
    String[] secondLayout ={"","1","","2","","3","","4","","5","","6","","7","","8","","9","","0","","(","",")","","#","","-","","+","","*","","^",
            "","/","","|","","\\","","~","","=","","[","","]","","%","","<","",">","","&","",":","",";","","{","","}","",".","",",","","?","","!","","'",
            "","/","","។","","៕","","-","","@","","៛","","$","","€","","£"};

//    String[] secondLayout ={"១","1","២","2","៣","3","៤","4","៥","5","៦","6","៧","7","៨","8","៩","9","០","0","ឰ","ឫ","ឩ","ឱ",
//            "|","\\","~","=","<","[",">","]","%","{","&","}",":","៚",";","៙",".","ឳ",",","?","ៈ","!","៖","'","/","។","៕","-","@",
//            "៰","៱","ៜ","៲","៝","៳","៴","៵","៶","៷","៸","៹",
//            " ៓","᠁","᠂","᠃","᠄","᠅","᠆","᠇","᠈","᠉","ឝ","ឞ","᧠","᧡","᧣","᧤","᧦","᧥","᧱","᧲",
//            "᧳","᧴","᧵","᧶","@","៛","$"};
//    String[] secondLayoutg ={"១","1","២","2","៣","3","៤","4","៥","5","៦","6","៧","7","៨","8","៩","9","០","0","ឰ","ឫ","ឩ","ឱ",
//            "|","\\","~","=","<","[",">","]","%","{","&","}",":","៚",";","៙",".","ឳ",",","?","ៈ","!","៖","'","/","។","៕","-","@",
//            "៰","៱","ៜ","៲","៝","៳","៴","៵","៶","៷","៸","៹",
//            " ៓","᠁","᠂","᠃","᠄","᠅","᠆","᠇","᠈","᠉","ឝ","ឞ","᧠","᧡","᧣","᧤","᧦","᧥","᧱","᧲",
//            "᧳","᧴","᧵","᧶","@","៛","$"};
    //    ៎  ៓   ៘ ៹


    //display text into suggestion row
    private void setSuggestionText(StringBuffer inputString1, ArrayList<TextView> sugTextView){
        if (inputString1.length() == 0){
            suggestionRow.setVisibility(View.INVISIBLE);
        }
        else {
            suggestionRow.setVisibility(View.VISIBLE);
            List<String> suggest = query(inputString1);
            for (int a = 0; a < suggest.size(); a++){
                sugTextView.get(a).setText(suggest.get(a));
            }
        }

    }


    private void setRoundedBackground(ViewGroup viewGroup, View view){
        ArrayList<View> allView = (ArrayList<View>) getAllChildren(viewGroup);
        ArrayList<View> viewFrame = new ArrayList<View>();
        for (View eachView : allView){
            viewFrame.add((View)eachView.getParent());
        }

        for(View view1 : viewFrame){
            if (view1.getId() == view.getId())
                view1.setBackgroundResource(R.drawable.rounded_shape);
            else
                view1.setBackgroundResource(0);
        }

    }




    //query top 3 suggestion word from database
    private List<String> query (StringBuffer word){
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
        dbAccess.open();
        List<String> suggestion = dbAccess.getSuggestion(word, isAutoComplete);
        dbAccess.close();
        return suggestion;
    }

    //get all the children inside the viewgroup (last children of the tree)
    private List<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<>();

        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            //Do not add any parents, just add child elements
            result.addAll(getAllChildren(child));
        }
        return result;
    }


    //update priority
    void updatePriority(String word){
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
        dbAccess.open();
        dbAccess.updatePrio(word);
        dbAccess.close();
    }

    @Override
    public View onCreateInputView() {






        inputString = new StringBuffer();

        Log.d("PIUKeyboard", "Keyboard started");

        ic = getCurrentInputConnection();
        ViewGroup keyboardView = (ViewGroup)getLayoutInflater().inflate(R.layout.keyboard_layout, null);
        ViewGroup charSets = keyboardView.findViewById(R.id.char_sets);
        suggestionRow = keyboardView.findViewById(R.id.suggestions);
        final ViewGroup emojiHolder = keyboardView.findViewById(R.id.emojiHolder);
        View keySpace = keyboardView.findViewById(R.id.keySpace);
        View keyBackspace = keyboardView.findViewById(R.id.backspace);
        View  keyReturn = keyboardView.findViewById(R.id.returnKey);
        View  keyEmoji = keyboardView.findViewById(R.id.emoji);
        final View key123 = keyboardView.findViewById(R.id.key123);
        final View keykorkhor = keyboardView.findViewById(R.id.keyKorKhor);
        final View setting = keyboardView.findViewById(R.id.setting);
        final View emoFlag = keyboardView.findViewById(R.id.emo_flag);
        final View emoActivities = keyboardView.findViewById(R.id.emo_activities);
        final View emoPeople = keyboardView.findViewById(R.id.emo_people);
        final View emoFood = keyboardView.findViewById(R.id.emo_food);
        final View emoObject = keyboardView.findViewById(R.id.emo_object);
        final View emoObject1 = keyboardView.findViewById(R.id.emo_object1);
        final View emoSmiley = keyboardView.findViewById(R.id.emo_smiley);
        final View emoNature = keyboardView.findViewById(R.id.emo_nature);
        final View emoTransport = keyboardView.findViewById(R.id.emo_transport);
        final ViewGroup emo_holder = keyboardView.findViewById(R.id.emojiHolder);



        recyclerView = keyboardView.findViewById(R.id.myRecylerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 9);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(emo_flag);
        recyclerView.setAdapter(mAdapter);
        Log.d("PIUKeyboard", "Set adapter: ");

        //get all view from layout exclude suggestion row
        ArrayList<View> allView = (ArrayList<View>) getAllChildren(charSets);

        //suggestion row

        //store suggestion textView
        final ArrayList<TextView> sugTextView = new ArrayList<>();

        //store suggestion key
        final ArrayList<View> suggestionKey = new ArrayList<>();

        //get view from suggestion row
        final ArrayList<View> suggestionsView = (ArrayList<View>) getAllChildren(suggestionRow);

        //get only TextView of the suggestion row
        for (int i = 0; i < suggestionsView.size(); i++)
        {
            if (suggestionsView.get(i) instanceof TextView)
                sugTextView.add((TextView)suggestionsView.get(i));

        }

        //get key of each suggestion
        for (int i = 0; i<suggestionsView.size(); i++){
            suggestionKey.add((View) suggestionsView.get(i).getParent());
        }

        final ArrayList<TextView> allTextView = new ArrayList<>(); // store only the TextView (the characters)
        final ArrayList<View> allFrameLayout = new ArrayList<>(); //store key of the keyboard

        for (int i = 0; i < allView.size(); i++) //get TextView from the layout {total 85 need only 82}
        {
            if (allView.get(i) instanceof TextView)
            allTextView.add((TextView)allView.get(i));

        }

        for (int i = 0; i < allView.size(); i++) //get key from the layout
        {
            if (i % 2 != 0)
            {
                allFrameLayout.add((View) allView.get(i).getParent());
            }
        }


        //default layout
        for (int i = 0; i<92; i++)
        {
            allTextView.get(i).setText(charAll[i]);
        }

        int k = 1;

        //get text from the key to the text area
        for (int i = 0; i < allFrameLayout.size(); i++)
        {

            final int j = k;
            allFrameLayout.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //on click listener
                    isAutoComplete = true;
                    ic.commitText(allTextView.get(j).getText(), 1);
                    inputString.append(allTextView.get(j).getText());
                    setSuggestionText(inputString, sugTextView);
                }
            });
            k += 2;
            allFrameLayout.get(i).setOnTouchListener(new OnSwipeTouchListener(){
                public boolean onSwipeTop() { // swipeUp listener
                    isAutoComplete = true;
                    ic.commitText(allTextView.get(j-1).getText(), 1);
                    inputString.append(allTextView.get(j).getText());
                    setSuggestionText(inputString, sugTextView);
                    return true;
                }
            });
        }

        //submit key event (Enter | Return | Done)
        keyReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                inputString.delete(0, inputString.length()-1);
            }
        });

        keyBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAutoComplete = true;
                ic.deleteSurroundingText(1,0);
                curPos = ic.getTextBeforeCursor(300,0).length();
                Log.d("PIUKeyboard", "CurrentPosition: "+curPos);

                if (inputString.length() > 0){
                    if (curPos>inputString.length()){
                        curPos = inputString.length()-1;
                    }
                    inputString.deleteCharAt(curPos);
//                    Log.d("PIUKeyboard", "inputString Value: " + inputString);

                }
                setSuggestionText(inputString, sugTextView);
                Log.d("PIUKeyboard", "inputString Value: " + inputString);

            }
        });

        keySpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence textBeforeCursor = ic.getTextBeforeCursor(1, 0);
                Log.d("PIUKeyboard", "text before cursor: "+textBeforeCursor);
                if (textBeforeCursor.toString().equals(" ")){
//                    Log.d("PIUKeyboard", "input String value:"+inputString);

                    inputString = new StringBuffer();
                    Log.d("PIUKeyboard", "input String has been renewed");
                }
                else {
                    ic.commitText(" ", 1);
                    inputString.append(" ");
                }
                Log.d("PIUKeyboard", "input String value:"+inputString);

            }
        });

        //switch keyboard
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                imeManager.showInputMethodPicker();
            }
        });

        int l=0;

        //select Text from Suggestion row into Text area
        for (int i = 0; i < suggestionsView.size(); i++){
            final int b = l;
            suggestionKey.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //auto complete
                    if (isAutoComplete){
                        ic.deleteSurroundingText(inputString.length(),0);
                        inputString = new StringBuffer(sugTextView.get(b).getText());
                        ic.commitText(inputString, 1);
                        Log.d("PIUKeyboard", "inputString value:" +inputString.length()+" ");
                        updatePriority((String) sugTextView.get(b).getText());
                        isAutoComplete = false;
                    }
                    //next word
                    else{
                        ic.commitText(sugTextView.get(b).getText(), 1);
                        inputString.append(sugTextView.get(b).getText());
                        Log.d("PIUKeyboard", "inputString value:" +inputString.length()+" ");
                    }
                    setSuggestionText( new StringBuffer(sugTextView.get(b).getText().toString()) , sugTextView);
                    updatePriority((String) sugTextView.get(b).getText());
                }
            });
            l++;
        }
        keyEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
                suggestionRow.setVisibility(View.GONE);
                emojiHolder.setVisibility(View.VISIBLE);
            }
        });

        key123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i<92; i++)
                {
                    allTextView.get(i).setText(secondLayout[i]);
                }

                recyclerView.setVisibility(View.GONE);
                suggestionRow.setVisibility(View.VISIBLE);
                emojiHolder.setVisibility(View.GONE);
                key123.setVisibility(View.GONE);
                keykorkhor.setVisibility(View.VISIBLE);



            }
        });

        keykorkhor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i<92; i++)
                {
                    allTextView.get(i).setText(charAll[i]);
                }
                recyclerView.setVisibility(View.GONE);
                suggestionRow.setVisibility(View.VISIBLE);
                emojiHolder.setVisibility(View.GONE);
                key123.setVisibility(View.VISIBLE);
                keykorkhor.setVisibility(View.GONE);

            }
        });


        emoFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoundedBackground(emo_holder, emoFlag);
                mAdapter = new MyAdapter(emo_flag);
                recyclerView.setAdapter(mAdapter);
            }
        });
        emoSmiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoundedBackground(emo_holder, emoSmiley);
                mAdapter = new MyAdapter(emo_smiley);
                recyclerView.setAdapter(mAdapter);
            }
        });
        emoPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoundedBackground(emo_holder, emoPeople);
                mAdapter = new MyAdapter(emo_people);
                recyclerView.setAdapter(mAdapter);
            }
        });
        emoNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoundedBackground(emo_holder, emoNature);
                mAdapter = new MyAdapter(emo_nature);
                recyclerView.setAdapter(mAdapter);
            }
        });
        emoTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoundedBackground(emo_holder, emoTransport);
                mAdapter = new MyAdapter(emo_transport);
                recyclerView.setAdapter(mAdapter);
            }
        });
        emoFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoundedBackground(emo_holder, emoFood);
                mAdapter = new MyAdapter(emo_food);
                recyclerView.setAdapter(mAdapter);
            }
        });
        emoActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoundedBackground(emo_holder, emoActivities);
                mAdapter = new MyAdapter(emo_activities);
                recyclerView.setAdapter(mAdapter);
            }
        });
        emoObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoundedBackground(emo_holder, emoObject);
                mAdapter = new MyAdapter(emo_object);
                recyclerView.setAdapter(mAdapter);
            }
        });
        emoObject1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoundedBackground(emo_holder, emoObject1);
                mAdapter = new MyAdapter(emo_object1);
                recyclerView.setAdapter(mAdapter);
            }
        });








        return keyboardView;
    }










    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



        private String[] mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder


        public void setmDataset(String[] mDataset) {
            this.mDataset = mDataset;
            notifyDataSetChanged();
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(String[] myDataset) {

            mDataset = myDataset;
            Log.d("PIUKeyboard", "MyAdapter: "+mDataset.length);
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.emoji_key, parent, false);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element



            holder.textView.setText(mDataset[position]);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {

            Log.d("PIUKeyboard", "getItemCount:"+mDataset.length);

            return mDataset.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView textView;
            public MyViewHolder(View v) {
                super(v);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String emoji = mDataset[getAdapterPosition()];
                        Log.d("PIUKeyboard", "emoji clicked");
                        ic.commitText(emoji,1);
                    }
                });

                textView = v.findViewById(R.id.textVieww);
            }
        }
    }
}


