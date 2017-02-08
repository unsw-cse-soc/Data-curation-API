package unsw.curation.api.domain;




public class Classification {

	public Classification(){}
	public Classification(double pre,double recall,double auc,double correct,double inCorrect, double errorRate,
			double fn,double fp,double tn,double tp,double kappa,double MAbsError,
			double numInstances,double relAbsErorr,double fMeasure)
	{
		this.precision=pre;
		this.recall=recall;
		this.auc=auc;
		this.incorrect=inCorrect;
		this.correct=correct;
		this.errorRate=errorRate;
		this.fn=fn;
		this.fp=fp;
		this.tn=tn;
		this.tp=tp;
		this.kappa=kappa;
		this.meanAbsoluteError=MAbsError;
		this.numInstances=numInstances;
		this.relativeAbsoluteError=relAbsErorr;
		this.fMeasure=fMeasure;
	}
	private double precision;
	private double recall;
	private double auc;
	private double correct;
	private double incorrect;
	private double errorRate;
	private double fn;
	private double fp;
	private double tn;
	private double tp;
	private double kappa;
	private double meanAbsoluteError;
	private double numInstances;
	private double relativeAbsoluteError;
	private double fMeasure;
	
	public void setInCorrect(double incorrect)
	{
		this.incorrect=incorrect;
	}
	public double getInCorrect()
	{
		return this.incorrect;
	}
	
	public void setCorrect(double correct)
	{
		this.correct=correct;
	}
	public double getCorrect()
	{
		return this.correct;
	}
	public void setPrecision(double precision)
	{
		this.precision=precision;
	}
	public double getPrecision()
	{
		return this.precision;
	}
	public void setRecall(double recall)
	{
		this.recall=recall;
	}
	public double getRecall()
	{
		return this.recall;
	}
	public void setAuc(double auc)
	{
		this.auc=auc;
	}
	public double getAuc()
	{
		return this.auc;
	}
	public void setErrorRate(double errorRate)
	{
		this.errorRate=errorRate;
	}
	public double getErrorRate()
	{
		return this.errorRate;
	}
	public void setFn(double fn)
	{
		this.fn=fn;
	}
	public double getFn()
	{
		return this.fn;
	}
	public void setFp(double fp)
	{
		this.fp=fp;
	}
	public double getFp()
	{
		return this.fp;
	}
	public void setTn(double tn)
	{
		this.tn=tn;
	}
	public double getTn()
	{
		return this.tn;
	}
	public void setTp(double tp)
	{
		this.tp=tp;
	}
	public double getTp()
	{
		return tp;
	}
	public void setKappa(double kappa)
	{
		this.kappa=kappa;
	}
	public double getKappa()
	{
		return this.kappa;
	}
	public void setMeanAbsoluteError(double meanAbsoluteError)
	{
		this.meanAbsoluteError=meanAbsoluteError;
	}
	public double getMeanAbsoluteError()
	{
		return this.meanAbsoluteError;
	}
	public void setNumInstances(double d)
	{
		this.numInstances=d;
	}
	public double getNumInstances()
	{
		return this.numInstances;
	}
	public void setRelativeAbsoluteError(double relativeAbsoluteError)
	{
		this.relativeAbsoluteError=relativeAbsoluteError;
	}
	public double getRelativeAbsoluteError()
	{
		return this.relativeAbsoluteError;
	}
	public void setFMeasure(double fMeasure)
	{
		this.fMeasure=fMeasure;
	}
	public double getFMeasure()
	{
		return this.fMeasure;
	}
}
