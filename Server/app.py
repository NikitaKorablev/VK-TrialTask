from flask import Flask, jsonify
import os


app = Flask(__name__)

top_videos = [
    {
        "name": "Christmas lofi сборник музыки для новогоднего уюта 🎄",
        "duration": "1:00:00",
        "url": "https://vkvd369.okcdn.ru/?expires=1740483433964&srcIp=186.2.226.83&pr=40&srcAg=CHROME&ms=185.226.53.204&type=3&sig=SWsuZfQ45y4&ct=0&urls=45.136.22.165&clientType=13&appId=512000384397&id=7510931147354",
    },
    {
        "name": "Zenless Zone Zero x Lofi Girl – Зенлесс Плейлист",
        "duration": "20:59",
        "url": "https://vkvd329.okcdn.ru/?expires=1740485384566&srcIp=106.219.182.177&pr=40&srcAg=CHROME&ms=185.226.53.166&type=3&sig=5Dq_SWjW-YE&ct=0&urls=45.136.20.151&clientType=13&appId=512000384397&id=7077589224076",
    },
    {
        "name": "Лофи плейлист - LoFi музыка для работы и отдыха",
        "duration": "58:48",
        "url": "https://vkvd477.okcdn.ru/?expires=1740485311528&srcIp=188.253.211.62&pr=40&srcAg=CHROME&ms=185.226.53.221&type=3&sig=k3XxE5fOtO8&ct=0&urls=185.226.52.141&clientType=13&appId=512000384397&id=7494734121473",
    },
    {
        "name": "Hot Chocolate | Cozy Beats to Relax, Study, Chill",
        "duration": "32:14",
        "url": "https://rr2---sn-2uja-aixr.googlevideo.com/videoplayback?expire=1740249660&ei=3MW5Z8jIArCu6dsPlO3KoAY&ip=39.45.160.231&id=o-ALf8RdyBgC8hF6UrHb7ZbSBEgLYyW8-O451G_e-hV-KJ&itag=18&source=youtube&requiressl=yes&xpc=EgVo2aDSNQ%3D%3D&met=1740228060%2C&mh=iK&mm=31%2C29&mn=sn-2uja-aixr%2Csn-npoe7nsr&ms=au%2Crdu&mv=m&mvi=2&pl=20&rms=au%2Cau&initcwndbps=516250&bui=AUWDL3xL-WCAIojK620_DHXy8-m1BXfLRdRQl_dZf89sTKj00CCBq8WI6QbJbyFyx4ddqT8SPRb40AoY&spc=RjZbSfRlHchDivBqlT2koX2BBtcI18RwXJFJkWmbxMT1JOg8aT5SbCeScvtXRCvVCPRnMg&vprv=1&svpuc=1&mime=video%2Fmp4&ns=bWJfX23lK0zOjKydb5U8wmEQ&rqh=1&cnr=14&ratebypass=yes&dur=1934.059&lmt=1736225064188202&mt=1740227592&fvip=1&fexp=51326932%2C51331020&c=MWEB&sefc=1&txp=4438534&n=MxMtSh-YMonuEQ&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cxpc%2Cbui%2Cspc%2Cvprv%2Csvpuc%2Cmime%2Cns%2Crqh%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AJfQdSswRQIhAOomWWSLuBqwkcER3-Pe3p-g-KMfI8KAjGqR_W8c52mpAiAYISSxQQNNHR6kCUrvACcazxUJch1r4NDNztKaVTFHWQ%3D%3D&lsparams=met%2Cmh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Crms%2Cinitcwndbps&lsig=AGluJ3MwRQIgVMyvg5qNdNJCG6ycx8DWhLFzyd8iYCQC0hp2mcPhv4sCIQCdZen0vju_at-_fR-ajYh-1s1aUpM31KTqbqF_LC_c6w%3D%3D&title=%E2%98%95%20Hot%20Chocolate%20%7C%20Cozy%20Beats%20to%20Relax%2C%20Study%2C%20Chill",
    },
    {
        "name": "/𝐧𝐨 𝐬𝐭𝐚𝐫𝐬 | 80's Tokyo Funky Lofi Playlist 🎧 | Broadcasting Beyond | Relax & Chill & Study to",
        "duration": "1:50:24",
        "url": "https://rr4---sn-uxaxjvhxbt2u-j5pl6.googlevideo.com/videoplayback?expire=1740249743&ei=L8a5Z6C4MoP5xN8PvICSsQo&ip=197.37.216.187&id=o-AClmNC4B1Udyoi3VXiR02IkhRYcbImr3-YYCtSXzF25-&itag=18&source=youtube&requiressl=yes&xpc=EgVo2aDSNQ%3D%3D&met=1740228143%2C&mh=QH&mm=31%2C29&mn=sn-uxaxjvhxbt2u-j5pl6%2Csn-4g5ednds&ms=au%2Crdu&mv=m&mvi=4&pl=18&rms=au%2Cau&initcwndbps=586250&bui=AUWDL3xLHklQDcagc389vXqnJwEoq7u5_q0a8MHlV1sz4T6sTIySxMGJr4TSPOaKbTPG_oHMoQR3cEeu&spc=RjZbSfasa3dd543zGPK1ZeGww1cTviTACri6xqasLhqd0iObk31JVdqsO84k1pI4SM-GXw&vprv=1&svpuc=1&mime=video%2Fmp4&ns=PFXHuNPHF9uz0csbb2Z7HzIQ&rqh=1&gir=yes&clen=194500354&ratebypass=yes&dur=6624.862&lmt=1738059599769498&mt=1740227592&fvip=4&fexp=51326932%2C51331020&c=MWEB&sefc=1&txp=5538534&n=-bFEzWjaTcOFZg&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cxpc%2Cbui%2Cspc%2Cvprv%2Csvpuc%2Cmime%2Cns%2Crqh%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AJfQdSswRQIhAJe7TQfU_cuHuh-bD-qNKrzhMYps-4ALuXLeGbKDRiacAiAoQ-676Rf0wQansxhjwK9PiDHg6yaPeCAjUHa1_I_mlw%3D%3D&lsparams=met%2Cmh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Crms%2Cinitcwndbps&lsig=AGluJ3MwRgIhAPpMrF7eP9tSM_P4dMT8WuKcfTJIV6Ud0uRrSGhCx6-7AiEAk72IQm0YP20IDtQLpjeJvOKmlyBJLlB6U9SKluKiTHk%3D&title=%2F%F0%9D%90%A7%F0%9D%90%A8%20%F0%9D%90%AC%F0%9D%90%AD%F0%9D%90%9A%F0%9D%90%AB%F0%9D%90%AC%20%7C%2080%27s%20Tokyo%20Funky%20Lofi%20Playlist%20%F0%9F%8E%A7%20%7C%20Broadcasting%20Beyond%20%7C%20Relax%20%26%20Chill%20%26%20Study%20to",
    }
]

@app.route("/top_videos", methods=["GET"])
def hello_world():
    return jsonify(top_videos)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=int(os.getenv("PORT", "5532")))