function chartExtender() {
    this.cfg.seriesDefaults.rendererOptions.varyBarColor = true;
    this.cfg.axes.yaxis.tickOptions = {
        formatString: '%d'
    };

    this.cfg.axes.xaxis.tickOptions = {
        labelPosition: 'middle'
    };
}
