class Camera{
    constructor(node){
            this.node = node;
    }
    powerOn(){
        console.log('PRENDIENDO_CAMARA');
        navigator.mediaDevices.getUserMedia({
            audio:false,
            video: {width: 300,height:300}
        }).then((stream)=>{
            this.node.srcObject = stream;
            this.stream = stream;
        })
    }

    powerOff(){
        console.log('APAGANDO_CAMARA');
        this.node.pause();
        if(this.stream) this.stream.getTracks()[0].stop();
    }

    takeAPhoto(){
        console.log('TOMANDO_FOTO');
        let canvas = document.createElement('canvas');
        canvas.setAttribute('width',300);
        canvas.setAttribute('height',300);
        let context = canvas.getContext('2d');
        context.drawImage(this.node,3,3,canvas.width,canvas.height)
        this.photo = context.canvas.toDataURL();
        canvas=null;
        context=null;
        return this.photo;
    }
}