package com.kamshanski.blepositioning.model.methods.solvers.classificators;

public class SvrKalman extends Classificator {
    @Override
    double computeX(double[] input) {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((-1.3083734784169765) + ((Math.exp((-0.1) * ((((Math.pow((0.4469529694444444) - (input[0]), 2.0)) + (Math.pow((0.716795085888889) - (input[1]), 2.0))) + (Math.pow((0.8299905471111111) - (input[2]), 2.0))) + (Math.pow((0.7815420402222222) - (input[3]), 2.0))))) * (66.66254009933995))) + ((Math.exp((-0.1) * ((((Math.pow((0.4660194657777778) - (input[0]), 2.0)) + (Math.pow((0.7863371595555555) - (input[1]), 2.0))) + (Math.pow((0.7650873293333333) - (input[2]), 2.0))) + (Math.pow((0.7745130296666667) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5921209413333334) - (input[0]), 2.0)) + (Math.pow((0.7692266263333333) - (input[1]), 2.0))) + (Math.pow((0.8133943032222222) - (input[2]), 2.0))) + (Math.pow((0.7481914303333335) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.734066046888889) - (input[0]), 2.0)) + (Math.pow((0.7916690952222223) - (input[1]), 2.0))) + (Math.pow((0.8379900336666667) - (input[2]), 2.0))) + (Math.pow((0.6844447291111111) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6835706653333333) - (input[0]), 2.0)) + (Math.pow((0.7302581183333334) - (input[1]), 2.0))) + (Math.pow((0.7475932101111111) - (input[2]), 2.0))) + (Math.pow((0.7288395437777778) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.702437302888889) - (input[0]), 2.0)) + (Math.pow((0.7668971787777779) - (input[1]), 2.0))) + (Math.pow((0.7272417846666667) - (input[2]), 2.0))) + (Math.pow((0.7034684296666667) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.8141956465555555) - (input[0]), 2.0)) + (Math.pow((0.603313525) - (input[1]), 2.0))) + (Math.pow((0.8015398742222222) - (input[2]), 2.0))) + (Math.pow((0.6945567187777778) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7866930408888889) - (input[0]), 2.0)) + (Math.pow((0.6516875781111111) - (input[1]), 2.0))) + (Math.pow((0.7484953423333334) - (input[2]), 2.0))) + (Math.pow((0.7079205558888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6235100006666667) - (input[0]), 2.0)) + (Math.pow((0.744722668111111) - (input[1]), 2.0))) + (Math.pow((0.762833993) - (input[2]), 2.0))) + (Math.pow((0.768739067) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6256308906666667) - (input[0]), 2.0)) + (Math.pow((0.733821489111111) - (input[1]), 2.0))) + (Math.pow((0.7365423052222222) - (input[2]), 2.0))) + (Math.pow((0.7797606201111111) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7745992237777778) - (input[0]), 2.0)) + (Math.pow((0.6704642404444444) - (input[1]), 2.0))) + (Math.pow((0.7320443398888888) - (input[2]), 2.0))) + (Math.pow((0.8059802222222222) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6095212164444445) - (input[0]), 2.0)) + (Math.pow((0.8141836283333334) - (input[1]), 2.0))) + (Math.pow((0.7890362202222222) - (input[2]), 2.0))) + (Math.pow((0.6908904103333333) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7371162187777778) - (input[0]), 2.0)) + (Math.pow((0.7144991508888888) - (input[1]), 2.0))) + (Math.pow((0.7859029146666666) - (input[2]), 2.0))) + (Math.pow((0.6812673364444445) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7026610973333333) - (input[0]), 2.0)) + (Math.pow((0.7011916582222223) - (input[1]), 2.0))) + (Math.pow((0.7694491941111111) - (input[2]), 2.0))) + (Math.pow((0.683024474) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7724270566666667) - (input[0]), 2.0)) + (Math.pow((0.5927672548888889) - (input[1]), 2.0))) + (Math.pow((0.764033049) - (input[2]), 2.0))) + (Math.pow((0.6735847164444444) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7962513548888889) - (input[0]), 2.0)) + (Math.pow((0.6253647626666666) - (input[1]), 2.0))) + (Math.pow((0.6860509453333333) - (input[2]), 2.0))) + (Math.pow((0.6710286155555555) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7874974206666667) - (input[0]), 2.0)) + (Math.pow((0.4659644301111111) - (input[1]), 2.0))) + (Math.pow((0.7880925691111111) - (input[2]), 2.0))) + (Math.pow((0.7432395097777778) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5036490233333334) - (input[0]), 2.0)) + (Math.pow((0.7405848115555556) - (input[1]), 2.0))) + (Math.pow((0.8485282773333334) - (input[2]), 2.0))) + (Math.pow((0.7855783272222223) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5056108243333333) - (input[0]), 2.0)) + (Math.pow((0.6915525576666667) - (input[1]), 2.0))) + (Math.pow((0.7329162028888889) - (input[2]), 2.0))) + (Math.pow((0.6866410687777779) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5651278988888889) - (input[0]), 2.0)) + (Math.pow((0.7116458095555557) - (input[1]), 2.0))) + (Math.pow((0.7249114918888889) - (input[2]), 2.0))) + (Math.pow((0.7190192308888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5726126998888889) - (input[0]), 2.0)) + (Math.pow((0.7195811241111111) - (input[1]), 2.0))) + (Math.pow((0.6828222423333333) - (input[2]), 2.0))) + (Math.pow((0.7116294847777778) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5538227161111111) - (input[0]), 2.0)) + (Math.pow((0.7499615473333334) - (input[1]), 2.0))) + (Math.pow((0.7066032404444444) - (input[2]), 2.0))) + (Math.pow((0.6820248578888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5545199842222223) - (input[0]), 2.0)) + (Math.pow((0.6868279467777778) - (input[1]), 2.0))) + (Math.pow((0.7574666346666666) - (input[2]), 2.0))) + (Math.pow((0.8232945161111112) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6946412843333334) - (input[0]), 2.0)) + (Math.pow((0.6662785036666666) - (input[1]), 2.0))) + (Math.pow((0.7482919037777778) - (input[2]), 2.0))) + (Math.pow((0.8211342888888888) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5935488363333333) - (input[0]), 2.0)) + (Math.pow((0.7269787022222223) - (input[1]), 2.0))) + (Math.pow((0.7488674597777778) - (input[2]), 2.0))) + (Math.pow((0.7666768586666666) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5835820077777778) - (input[0]), 2.0)) + (Math.pow((0.7366164101111111) - (input[1]), 2.0))) + (Math.pow((0.8160076657777777) - (input[2]), 2.0))) + (Math.pow((0.7165324598888888) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6102252109999999) - (input[0]), 2.0)) + (Math.pow((0.7010450474444444) - (input[1]), 2.0))) + (Math.pow((0.720334521) - (input[2]), 2.0))) + (Math.pow((0.6003755517777778) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7012636307777778) - (input[0]), 2.0)) + (Math.pow((0.6997309402222223) - (input[1]), 2.0))) + (Math.pow((0.7304725301111111) - (input[2]), 2.0))) + (Math.pow((0.628156108) - (input[3]), 2.0))))) * (-73.74077419982707))) + ((Math.exp((-0.1) * ((((Math.pow((0.67966212) - (input[0]), 2.0)) + (Math.pow((0.6787458001111111) - (input[1]), 2.0))) + (Math.pow((0.6379819453333333) - (input[2]), 2.0))) + (Math.pow((0.7027349598888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7009524115555555) - (input[0]), 2.0)) + (Math.pow((0.7485134618888889) - (input[1]), 2.0))) + (Math.pow((0.6234662497777778) - (input[2]), 2.0))) + (Math.pow((0.7263220937777777) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6288249895555555) - (input[0]), 2.0)) + (Math.pow((0.6527159673333334) - (input[1]), 2.0))) + (Math.pow((0.7499976338888888) - (input[2]), 2.0))) + (Math.pow((0.6597700164444444) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6953139836666666) - (input[0]), 2.0)) + (Math.pow((0.7334781589999999) - (input[1]), 2.0))) + (Math.pow((0.8112018372222222) - (input[2]), 2.0))) + (Math.pow((0.6263139501111111) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6746867658888889) - (input[0]), 2.0)) + (Math.pow((0.7504726624444444) - (input[1]), 2.0))) + (Math.pow((0.8109131372222222) - (input[2]), 2.0))) + (Math.pow((0.612753341888889) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6869404072222223) - (input[0]), 2.0)) + (Math.pow((0.7196595573333334) - (input[1]), 2.0))) + (Math.pow((0.6273437114444445) - (input[2]), 2.0))) + (Math.pow((0.6712701382222223) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6847497692222222) - (input[0]), 2.0)) + (Math.pow((0.7749151931111111) - (input[1]), 2.0))) + (Math.pow((0.6501683207777778) - (input[2]), 2.0))) + (Math.pow((0.6864168322222223) - (input[3]), 2.0))))) * (-74.47785181849773))) + ((Math.exp((-0.1) * ((((Math.pow((0.7301150828888888) - (input[0]), 2.0)) + (Math.pow((0.7800967173333333) - (input[1]), 2.0))) + (Math.pow((0.7302252894444444) - (input[2]), 2.0))) + (Math.pow((0.6317327146666667) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6615825638888889) - (input[0]), 2.0)) + (Math.pow((0.6869834636666667) - (input[1]), 2.0))) + (Math.pow((0.6909914954444444) - (input[2]), 2.0))) + (Math.pow((0.7234830503333334) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6690108291111111) - (input[0]), 2.0)) + (Math.pow((0.7148038617777778) - (input[1]), 2.0))) + (Math.pow((0.6770502662222222) - (input[2]), 2.0))) + (Math.pow((0.6730660271111111) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7158116946666666) - (input[0]), 2.0)) + (Math.pow((0.8153813792222222) - (input[1]), 2.0))) + (Math.pow((0.7659812117777777) - (input[2]), 2.0))) + (Math.pow((0.6997471891111111) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7017873337777778) - (input[0]), 2.0)) + (Math.pow((0.7574948556666666) - (input[1]), 2.0))) + (Math.pow((0.6523200746666666) - (input[2]), 2.0))) + (Math.pow((0.7219520759999999) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.701812357) - (input[0]), 2.0)) + (Math.pow((0.7226361866666666) - (input[1]), 2.0))) + (Math.pow((0.549314886) - (input[2]), 2.0))) + (Math.pow((0.6840909218888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7036558578888888) - (input[0]), 2.0)) + (Math.pow((0.72034756) - (input[1]), 2.0))) + (Math.pow((0.6574837031111112) - (input[2]), 2.0))) + (Math.pow((0.7462397557777777) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.8178835045555556) - (input[0]), 2.0)) + (Math.pow((0.7075460956666666) - (input[1]), 2.0))) + (Math.pow((0.6653910430000001) - (input[2]), 2.0))) + (Math.pow((0.6359085593333333) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6791740292222223) - (input[0]), 2.0)) + (Math.pow((0.7195621454444444) - (input[1]), 2.0))) + (Math.pow((0.6746685303333333) - (input[2]), 2.0))) + (Math.pow((0.5849290235555555) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6794505063333334) - (input[0]), 2.0)) + (Math.pow((0.6931489992222222) - (input[1]), 2.0))) + (Math.pow((0.6392868166666668) - (input[2]), 2.0))) + (Math.pow((0.6521970684444445) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6938055678888889) - (input[0]), 2.0)) + (Math.pow((0.7056884426666667) - (input[1]), 2.0))) + (Math.pow((0.6004892033333333) - (input[2]), 2.0))) + (Math.pow((0.7633119411111112) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7060460133333333) - (input[0]), 2.0)) + (Math.pow((0.7522043558888888) - (input[1]), 2.0))) + (Math.pow((0.6246261455555555) - (input[2]), 2.0))) + (Math.pow((0.752764611888889) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6990123657777778) - (input[0]), 2.0)) + (Math.pow((0.7175524305555555) - (input[1]), 2.0))) + (Math.pow((0.549982223888889) - (input[2]), 2.0))) + (Math.pow((0.8005014022222222) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7061722641111111) - (input[0]), 2.0)) + (Math.pow((0.7246680094444444) - (input[1]), 2.0))) + (Math.pow((0.48878555288888886) - (input[2]), 2.0))) + (Math.pow((0.6684390515555555) - (input[3]), 2.0))))) * (-0.7953527284104569))) + ((Math.exp((-0.1) * ((((Math.pow((0.7031534213333334) - (input[0]), 2.0)) + (Math.pow((0.7168331267777778) - (input[1]), 2.0))) + (Math.pow((0.675372785) - (input[2]), 2.0))) + (Math.pow((0.38490350211111113) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7152901242222222) - (input[0]), 2.0)) + (Math.pow((0.7226148532222223) - (input[1]), 2.0))) + (Math.pow((0.7133717785555557) - (input[2]), 2.0))) + (Math.pow((0.48759044222222225) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7171661255555555) - (input[0]), 2.0)) + (Math.pow((0.7354877072222223) - (input[1]), 2.0))) + (Math.pow((0.59947059) - (input[2]), 2.0))) + (Math.pow((0.6486063652222223) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.8078412382222222) - (input[0]), 2.0)) + (Math.pow((0.7335898538888889) - (input[1]), 2.0))) + (Math.pow((0.43368846255555554) - (input[2]), 2.0))) + (Math.pow((0.6608484574444444) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7151593544444445) - (input[0]), 2.0)) + (Math.pow((0.7421418706666666) - (input[1]), 2.0))) + (Math.pow((0.6762598908888889) - (input[2]), 2.0))) + (Math.pow((0.5842871948888889) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7260420562222223) - (input[0]), 2.0)) + (Math.pow((0.7137081586666666) - (input[1]), 2.0))) + (Math.pow((0.6469028581111111) - (input[2]), 2.0))) + (Math.pow((0.65699718) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6800071777777777) - (input[0]), 2.0)) + (Math.pow((0.716632018) - (input[1]), 2.0))) + (Math.pow((0.6209023804444445) - (input[2]), 2.0))) + (Math.pow((0.6307789252222222) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6750291566666666) - (input[0]), 2.0)) + (Math.pow((0.7356367286666666) - (input[1]), 2.0))) + (Math.pow((0.6168575655555556) - (input[2]), 2.0))) + (Math.pow((0.6138214966666666) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7473236652222222) - (input[0]), 2.0)) + (Math.pow((0.7386180370000001) - (input[1]), 2.0))) + (Math.pow((0.5369434663333333) - (input[2]), 2.0))) + (Math.pow((0.6745934898888889) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7660350208888889) - (input[0]), 2.0)) + (Math.pow((0.7197891681111112) - (input[1]), 2.0))) + (Math.pow((0.34747161722222225) - (input[2]), 2.0))) + (Math.pow((0.6914093858888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7589123206666667) - (input[0]), 2.0)) + (Math.pow((0.381086667) - (input[1]), 2.0))) + (Math.pow((0.652950374) - (input[2]), 2.0))) + (Math.pow((0.7314177648888888) - (input[3]), 2.0))))) * (-20.872813897668415))) + ((Math.exp((-0.1) * ((((Math.pow((0.6887325902222222) - (input[0]), 2.0)) + (Math.pow((0.826763091) - (input[1]), 2.0))) + (Math.pow((0.8338738611111112) - (input[2]), 2.0))) + (Math.pow((0.4031698798888889) - (input[3]), 2.0))))) * (3.2242525450637385));
    }

    @Override
    double computeY(double[] input) {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((0.05380528145383634) + ((Math.exp((-0.1) * ((((Math.pow((0.702437302888889) - (input[0]), 2.0)) + (Math.pow((0.7668971787777779) - (input[1]), 2.0))) + (Math.pow((0.7272417846666667) - (input[2]), 2.0))) + (Math.pow((0.7034684296666667) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7866930408888889) - (input[0]), 2.0)) + (Math.pow((0.6516875781111111) - (input[1]), 2.0))) + (Math.pow((0.7484953423333334) - (input[2]), 2.0))) + (Math.pow((0.7079205558888889) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6235100006666667) - (input[0]), 2.0)) + (Math.pow((0.744722668111111) - (input[1]), 2.0))) + (Math.pow((0.762833993) - (input[2]), 2.0))) + (Math.pow((0.768739067) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6256308906666667) - (input[0]), 2.0)) + (Math.pow((0.733821489111111) - (input[1]), 2.0))) + (Math.pow((0.7365423052222222) - (input[2]), 2.0))) + (Math.pow((0.7797606201111111) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7745992237777778) - (input[0]), 2.0)) + (Math.pow((0.6704642404444444) - (input[1]), 2.0))) + (Math.pow((0.7320443398888888) - (input[2]), 2.0))) + (Math.pow((0.8059802222222222) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6095212164444445) - (input[0]), 2.0)) + (Math.pow((0.8141836283333334) - (input[1]), 2.0))) + (Math.pow((0.7890362202222222) - (input[2]), 2.0))) + (Math.pow((0.6908904103333333) - (input[3]), 2.0))))) * (5.530028554587507))) + ((Math.exp((-0.1) * ((((Math.pow((0.6726054958888888) - (input[0]), 2.0)) + (Math.pow((0.6263115467777778) - (input[1]), 2.0))) + (Math.pow((0.7965674873333334) - (input[2]), 2.0))) + (Math.pow((0.6914053693333333) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5651278988888889) - (input[0]), 2.0)) + (Math.pow((0.7116458095555557) - (input[1]), 2.0))) + (Math.pow((0.7249114918888889) - (input[2]), 2.0))) + (Math.pow((0.7190192308888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7552950628888889) - (input[0]), 2.0)) + (Math.pow((0.5643878534444444) - (input[1]), 2.0))) + (Math.pow((0.7845275541111112) - (input[2]), 2.0))) + (Math.pow((0.6793489378888888) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7387662359999999) - (input[0]), 2.0)) + (Math.pow((0.5309338398888889) - (input[1]), 2.0))) + (Math.pow((0.7325170207777778) - (input[2]), 2.0))) + (Math.pow((0.6733727783333333) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5726126998888889) - (input[0]), 2.0)) + (Math.pow((0.7195811241111111) - (input[1]), 2.0))) + (Math.pow((0.6828222423333333) - (input[2]), 2.0))) + (Math.pow((0.7116294847777778) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5538227161111111) - (input[0]), 2.0)) + (Math.pow((0.7499615473333334) - (input[1]), 2.0))) + (Math.pow((0.7066032404444444) - (input[2]), 2.0))) + (Math.pow((0.6820248578888889) - (input[3]), 2.0))))) * (-22.66822546371403))) + ((Math.exp((-0.1) * ((((Math.pow((0.5545199842222223) - (input[0]), 2.0)) + (Math.pow((0.6868279467777778) - (input[1]), 2.0))) + (Math.pow((0.7574666346666666) - (input[2]), 2.0))) + (Math.pow((0.8232945161111112) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6946412843333334) - (input[0]), 2.0)) + (Math.pow((0.6662785036666666) - (input[1]), 2.0))) + (Math.pow((0.7482919037777778) - (input[2]), 2.0))) + (Math.pow((0.8211342888888888) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7940173886666667) - (input[0]), 2.0)) + (Math.pow((0.656743999) - (input[1]), 2.0))) + (Math.pow((0.7762503073333333) - (input[2]), 2.0))) + (Math.pow((0.7315370393333332) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7354212147777778) - (input[0]), 2.0)) + (Math.pow((0.5963896058888889) - (input[1]), 2.0))) + (Math.pow((0.7523408004444445) - (input[2]), 2.0))) + (Math.pow((0.6959576088888889) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.701819064) - (input[0]), 2.0)) + (Math.pow((0.6172944394444444) - (input[1]), 2.0))) + (Math.pow((0.7460538948888888) - (input[2]), 2.0))) + (Math.pow((0.7336778007777778) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.5935488363333333) - (input[0]), 2.0)) + (Math.pow((0.7269787022222223) - (input[1]), 2.0))) + (Math.pow((0.7488674597777778) - (input[2]), 2.0))) + (Math.pow((0.7666768586666666) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7104303436666667) - (input[0]), 2.0)) + (Math.pow((0.7313264168888889) - (input[1]), 2.0))) + (Math.pow((0.7230468655555555) - (input[2]), 2.0))) + (Math.pow((0.6933604441111112) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7009524115555555) - (input[0]), 2.0)) + (Math.pow((0.7485134618888889) - (input[1]), 2.0))) + (Math.pow((0.6234662497777778) - (input[2]), 2.0))) + (Math.pow((0.7263220937777777) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6288249895555555) - (input[0]), 2.0)) + (Math.pow((0.6527159673333334) - (input[1]), 2.0))) + (Math.pow((0.7499976338888888) - (input[2]), 2.0))) + (Math.pow((0.6597700164444444) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6953139836666666) - (input[0]), 2.0)) + (Math.pow((0.7334781589999999) - (input[1]), 2.0))) + (Math.pow((0.8112018372222222) - (input[2]), 2.0))) + (Math.pow((0.6263139501111111) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6746867658888889) - (input[0]), 2.0)) + (Math.pow((0.7504726624444444) - (input[1]), 2.0))) + (Math.pow((0.8109131372222222) - (input[2]), 2.0))) + (Math.pow((0.612753341888889) - (input[3]), 2.0))))) * (-44.84111712098849))) + ((Math.exp((-0.1) * ((((Math.pow((0.6788532038888888) - (input[0]), 2.0)) + (Math.pow((0.6963635206666667) - (input[1]), 2.0))) + (Math.pow((0.7113022577777778) - (input[2]), 2.0))) + (Math.pow((0.6372209888888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6869404072222223) - (input[0]), 2.0)) + (Math.pow((0.7196595573333334) - (input[1]), 2.0))) + (Math.pow((0.6273437114444445) - (input[2]), 2.0))) + (Math.pow((0.6712701382222223) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6847497692222222) - (input[0]), 2.0)) + (Math.pow((0.7749151931111111) - (input[1]), 2.0))) + (Math.pow((0.6501683207777778) - (input[2]), 2.0))) + (Math.pow((0.6864168322222223) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.705799347111111) - (input[0]), 2.0)) + (Math.pow((0.7246930741111112) - (input[1]), 2.0))) + (Math.pow((0.6184841588888889) - (input[2]), 2.0))) + (Math.pow((0.727848317) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7301150828888888) - (input[0]), 2.0)) + (Math.pow((0.7800967173333333) - (input[1]), 2.0))) + (Math.pow((0.7302252894444444) - (input[2]), 2.0))) + (Math.pow((0.6317327146666667) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7370509023333333) - (input[0]), 2.0)) + (Math.pow((0.7918357254444445) - (input[1]), 2.0))) + (Math.pow((0.7203303381111111) - (input[2]), 2.0))) + (Math.pow((0.5890850418888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6615825638888889) - (input[0]), 2.0)) + (Math.pow((0.6869834636666667) - (input[1]), 2.0))) + (Math.pow((0.6909914954444444) - (input[2]), 2.0))) + (Math.pow((0.7234830503333334) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6901879201111112) - (input[0]), 2.0)) + (Math.pow((0.7497820708888889) - (input[1]), 2.0))) + (Math.pow((0.6741143044444444) - (input[2]), 2.0))) + (Math.pow((0.6674480684444445) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7158116946666666) - (input[0]), 2.0)) + (Math.pow((0.8153813792222222) - (input[1]), 2.0))) + (Math.pow((0.7659812117777777) - (input[2]), 2.0))) + (Math.pow((0.6997471891111111) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7017873337777778) - (input[0]), 2.0)) + (Math.pow((0.7574948556666666) - (input[1]), 2.0))) + (Math.pow((0.6523200746666666) - (input[2]), 2.0))) + (Math.pow((0.7219520759999999) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.701812357) - (input[0]), 2.0)) + (Math.pow((0.7226361866666666) - (input[1]), 2.0))) + (Math.pow((0.549314886) - (input[2]), 2.0))) + (Math.pow((0.6840909218888889) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7212274715555557) - (input[0]), 2.0)) + (Math.pow((0.7370119682222223) - (input[1]), 2.0))) + (Math.pow((0.6809847022222223) - (input[2]), 2.0))) + (Math.pow((0.6048183425555556) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7601790185555556) - (input[0]), 2.0)) + (Math.pow((0.7259240965555555) - (input[1]), 2.0))) + (Math.pow((0.683760275111111) - (input[2]), 2.0))) + (Math.pow((0.5420122401111112) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7322849227777777) - (input[0]), 2.0)) + (Math.pow((0.7133346778888889) - (input[1]), 2.0))) + (Math.pow((0.667961665) - (input[2]), 2.0))) + (Math.pow((0.571740361111111) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.711323237) - (input[0]), 2.0)) + (Math.pow((0.7236779878888888) - (input[1]), 2.0))) + (Math.pow((0.6443017394444445) - (input[2]), 2.0))) + (Math.pow((0.6422781405555555) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6921707146666667) - (input[0]), 2.0)) + (Math.pow((0.7242689478888888) - (input[1]), 2.0))) + (Math.pow((0.5973121113333333) - (input[2]), 2.0))) + (Math.pow((0.6004576507777778) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6990124353333333) - (input[0]), 2.0)) + (Math.pow((0.6921252803333333) - (input[1]), 2.0))) + (Math.pow((0.5106561111111111) - (input[2]), 2.0))) + (Math.pow((0.7076184555555556) - (input[3]), 2.0))))) * (9.083819873548455))) + ((Math.exp((-0.1) * ((((Math.pow((0.6539598132222223) - (input[0]), 2.0)) + (Math.pow((0.7479980661111111) - (input[1]), 2.0))) + (Math.pow((0.670847976) - (input[2]), 2.0))) + (Math.pow((0.47150210477777776) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6646279548888889) - (input[0]), 2.0)) + (Math.pow((0.7253890963333334) - (input[1]), 2.0))) + (Math.pow((0.691584266888889) - (input[2]), 2.0))) + (Math.pow((0.5054804753333334) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7036558578888888) - (input[0]), 2.0)) + (Math.pow((0.72034756) - (input[1]), 2.0))) + (Math.pow((0.6574837031111112) - (input[2]), 2.0))) + (Math.pow((0.7462397557777777) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.8178835045555556) - (input[0]), 2.0)) + (Math.pow((0.7075460956666666) - (input[1]), 2.0))) + (Math.pow((0.6653910430000001) - (input[2]), 2.0))) + (Math.pow((0.6359085593333333) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7061722641111111) - (input[0]), 2.0)) + (Math.pow((0.7246680094444444) - (input[1]), 2.0))) + (Math.pow((0.48878555288888886) - (input[2]), 2.0))) + (Math.pow((0.6684390515555555) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7566654284444444) - (input[0]), 2.0)) + (Math.pow((0.7119336905555554) - (input[1]), 2.0))) + (Math.pow((0.6614381291111111) - (input[2]), 2.0))) + (Math.pow((0.5261362795555555) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.692671157111111) - (input[0]), 2.0)) + (Math.pow((0.7333128966666667) - (input[1]), 2.0))) + (Math.pow((0.5577160752222222) - (input[2]), 2.0))) + (Math.pow((0.6276359166666666) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6859193321111111) - (input[0]), 2.0)) + (Math.pow((0.8149433285555555) - (input[1]), 2.0))) + (Math.pow((0.7212084024444445) - (input[2]), 2.0))) + (Math.pow((0.4232056303333333) - (input[3]), 2.0))))) * (52.895494156566514))) + ((Math.exp((-0.1) * ((((Math.pow((0.7151593544444445) - (input[0]), 2.0)) + (Math.pow((0.7421418706666666) - (input[1]), 2.0))) + (Math.pow((0.6762598908888889) - (input[2]), 2.0))) + (Math.pow((0.5842871948888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7260420562222223) - (input[0]), 2.0)) + (Math.pow((0.7137081586666666) - (input[1]), 2.0))) + (Math.pow((0.6469028581111111) - (input[2]), 2.0))) + (Math.pow((0.65699718) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6750291566666666) - (input[0]), 2.0)) + (Math.pow((0.7356367286666666) - (input[1]), 2.0))) + (Math.pow((0.6168575655555556) - (input[2]), 2.0))) + (Math.pow((0.6138214966666666) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7660350208888889) - (input[0]), 2.0)) + (Math.pow((0.7197891681111112) - (input[1]), 2.0))) + (Math.pow((0.34747161722222225) - (input[2]), 2.0))) + (Math.pow((0.6914093858888889) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.7589123206666667) - (input[0]), 2.0)) + (Math.pow((0.381086667) - (input[1]), 2.0))) + (Math.pow((0.652950374) - (input[2]), 2.0))) + (Math.pow((0.7314177648888888) - (input[3]), 2.0))))) * (-100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.6887325902222222) - (input[0]), 2.0)) + (Math.pow((0.826763091) - (input[1]), 2.0))) + (Math.pow((0.8338738611111112) - (input[2]), 2.0))) + (Math.pow((0.4031698798888889) - (input[3]), 2.0))))) * (100.0))) + ((Math.exp((-0.1) * ((((Math.pow((0.35604489255555555) - (input[0]), 2.0)) + (Math.pow((0.738680919) - (input[1]), 2.0))) + (Math.pow((0.8228545333333334) - (input[2]), 2.0))) + (Math.pow((0.726206057) - (input[3]), 2.0))))) * (100.0));
    }
}